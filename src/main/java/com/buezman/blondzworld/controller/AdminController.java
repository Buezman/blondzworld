package com.buezman.blondzworld.controller;

import com.buezman.blondzworld.request.UserRequest;
import com.buezman.blondzworld.response.UserResponse;
import com.buezman.blondzworld.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse registerStaff(@RequestBody UserRequest userRequest) {
        return adminService.registerStaff(userRequest);
    }
}
