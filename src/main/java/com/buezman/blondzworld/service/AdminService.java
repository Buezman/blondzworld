package com.buezman.blondzworld.service;

import com.buezman.blondzworld.request.ProductRequest;
import com.buezman.blondzworld.request.UserRequest;
import com.buezman.blondzworld.response.ProductResponse;
import com.buezman.blondzworld.response.UserResponse;

import java.io.IOException;

public interface AdminService {
    UserResponse registerStaff(UserRequest userRequest);
    ProductResponse addProduct(ProductRequest productRequest) throws IOException;
}
