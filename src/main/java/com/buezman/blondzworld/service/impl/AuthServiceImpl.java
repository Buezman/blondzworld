package com.buezman.blondzworld.service.impl;

import com.buezman.blondzworld.entity.Role;
import com.buezman.blondzworld.entity.User;
import com.buezman.blondzworld.enums.UserRole;
import com.buezman.blondzworld.exception.AppApiException;
import com.buezman.blondzworld.repository.RoleRepository;
import com.buezman.blondzworld.repository.UserRepository;
import com.buezman.blondzworld.request.LoginRequest;
import com.buezman.blondzworld.request.UserRequest;
import com.buezman.blondzworld.response.JwtAuthResponse;
import com.buezman.blondzworld.response.UserResponse;
import com.buezman.blondzworld.security.JwtProvider;
import com.buezman.blondzworld.service.AuthService;
import com.buezman.blondzworld.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtProvider jwtProvider;
    private final ModelMapper modelMapper;
    private final EmailService emailService;

    @Override
    public JwtAuthResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsernameOrEmail(), loginRequest.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        return new JwtAuthResponse("Login successful", "Bearer " + token, "Bearer");
    }

    @Override
    @Transactional
    public UserResponse clientSignUp(UserRequest userRequest) {
        validatePassword(userRequest.getPassword(), userRequest.getConfirmPassword());
        Role clientRole = roleRepository.findRoleByName("CLIENT").orElse(null);
        User client = User.builder()
                .firstname(userRequest.getFirstname())
                .lastname(userRequest.getLastname())
                .gender(userRequest.getGender())
                .email(userRequest.getEmail())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .username(userRequest.getUsername())
                .roles(Collections.singleton(clientRole))
                .build();
        userRepository.save(client);

        String registrationText = "Thank you for registering with Blondzworld Aesthetics, Enjoy first class treatments" +
                " and the best quality products for your beauty and wellness";

        emailService.sendEmail(userRequest.getEmail(), "ACCOUNT REGISTRATION", registrationText);

        return modelMapper.map(client, UserResponse.class);
    }

    @Override
    public void validateAdmin() {
        User loggedInUser = getLoggedInUser();
        boolean isAdmin = validateRole(loggedInUser, UserRole.ADMIN);
        if (!isAdmin) throw new AppApiException(HttpStatus.UNAUTHORIZED, "Not authorized to perform this operation");
    }

    private User getLoggedInUser() {
        String usernameOrEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        return userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    private boolean validateRole(User user, String roleName) {
        List<String> roles = new ArrayList<>();
        user.getRoles().forEach(role -> roles.add(role.getName()));
        return roles.contains(roleName);
    }


    private void validatePassword(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            throw new AppApiException(HttpStatus.BAD_REQUEST, "Password does not match");
        }
    }
}
