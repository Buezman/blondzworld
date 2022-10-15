package com.buezman.blondzworld.controller;

import com.buezman.blondzworld.request.LoginRequest;
import com.buezman.blondzworld.request.UserRequest;
import com.buezman.blondzworld.response.JwtAuthResponse;
import com.buezman.blondzworld.response.UserResponse;
import com.buezman.blondzworld.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("login")
    @ResponseStatus(HttpStatus.OK)
    public JwtAuthResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("signup")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse clientSignup(@RequestBody UserRequest userRequest) {
        return authService.clientSignUp(userRequest);
    }
}
