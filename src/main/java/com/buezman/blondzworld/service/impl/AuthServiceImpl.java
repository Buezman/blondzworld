package com.buezman.blondzworld.service.impl;

import com.buezman.blondzworld.repository.RoleRepository;
import com.buezman.blondzworld.repository.UserRepository;
import com.buezman.blondzworld.request.LoginRequest;
import com.buezman.blondzworld.response.JwtAuthResponse;
import com.buezman.blondzworld.security.JwtProvider;
import com.buezman.blondzworld.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtProvider jwtProvider;

    @Override
    public JwtAuthResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsernameOrEmail(), loginRequest.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        return new JwtAuthResponse("Login successful", "Bearer " + token, "Bearer");
    }
}
