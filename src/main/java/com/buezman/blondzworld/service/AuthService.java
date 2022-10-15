package com.buezman.blondzworld.service;

import com.buezman.blondzworld.entity.Role;
import com.buezman.blondzworld.entity.User;
import com.buezman.blondzworld.request.LoginRequest;
import com.buezman.blondzworld.request.UserRequest;
import com.buezman.blondzworld.response.JwtAuthResponse;
import com.buezman.blondzworld.response.UserResponse;

public interface AuthService {
    JwtAuthResponse login(LoginRequest loginRequest);
    UserResponse clientSignUp(UserRequest userRequest);
    void validateAdmin();
}
