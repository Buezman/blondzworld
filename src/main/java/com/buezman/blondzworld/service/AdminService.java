package com.buezman.blondzworld.service;

import com.buezman.blondzworld.request.UserRequest;
import com.buezman.blondzworld.response.UserResponse;

public interface AdminService {
    UserResponse registerStaff(UserRequest userRequest);
}
