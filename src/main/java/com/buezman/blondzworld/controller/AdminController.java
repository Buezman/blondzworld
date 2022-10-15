package com.buezman.blondzworld.controller;

import com.buezman.blondzworld.request.ProductRequest;
import com.buezman.blondzworld.request.UserRequest;
import com.buezman.blondzworld.response.ProductResponse;
import com.buezman.blondzworld.response.UserResponse;
import com.buezman.blondzworld.service.AdminService;
import com.buezman.blondzworld.service.impl.CloudinaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final CloudinaryService cloudinaryService;

    @PostMapping("staff")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse registerStaff(@RequestBody UserRequest userRequest) {
        return adminService.registerStaff(userRequest);
    }

    @PostMapping(name = "products", consumes = "multipart/form-data")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse addProduct(@ModelAttribute ProductRequest productRequest) throws IOException {
        return adminService.addProduct(productRequest);
    }
}
