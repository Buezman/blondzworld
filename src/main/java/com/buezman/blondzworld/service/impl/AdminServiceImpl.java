package com.buezman.blondzworld.service.impl;

import com.buezman.blondzworld.entity.Role;
import com.buezman.blondzworld.entity.User;
import com.buezman.blondzworld.enums.AppConstants;
import com.buezman.blondzworld.enums.UserRole;
import com.buezman.blondzworld.exception.AppApiException;
import com.buezman.blondzworld.exception.ResourceNotFoundException;
import com.buezman.blondzworld.repository.RoleRepository;
import com.buezman.blondzworld.repository.UserRepository;
import com.buezman.blondzworld.request.UserRequest;
import com.buezman.blondzworld.response.UserResponse;
import com.buezman.blondzworld.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse registerStaff(UserRequest userRequest) {
        String usernameOrEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User loggedInUser = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Role adminRole = roleRepository.findRoleByName("ADMIN")
                .orElseThrow(()->new ResourceNotFoundException("Role", "name", userRequest.getRole()));
        boolean isAdmin = validateRole(loggedInUser, adminRole);
        if (!isAdmin) throw new AppApiException(HttpStatus.UNAUTHORIZED, "Not authorized to perform this operation");
        Role role = roleRepository.findRoleByName(userRequest.getRole())
                .orElseThrow(()-> new ResourceNotFoundException("Role", "name", userRequest.getRole()));
        User user = modelMapper.map(userRequest, User.class);
        user.setPassword(passwordEncoder.encode(generatePassword()));
        user.setRoles(Collections.singleton(role));
        userRepository.save(user);

        return modelMapper.map(user, UserResponse.class);
    }

    private String generatePassword() {
        int length = 7;
        boolean useLetters = true;
        boolean useNumbers = true;
        String password = RandomStringUtils.random(length, useLetters, useNumbers);
        log.info(password);
        return password;
    }

    private boolean validateRole(User user, Role role) {
        return user.getRoles().contains(role);
    }
}
