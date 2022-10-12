package com.buezman.blondzworld.service;

import com.buezman.blondzworld.request.LoginRequest;
import com.buezman.blondzworld.response.JwtAuthResponse;

public interface AuthService {
    JwtAuthResponse login(LoginRequest loginRequest);
}
