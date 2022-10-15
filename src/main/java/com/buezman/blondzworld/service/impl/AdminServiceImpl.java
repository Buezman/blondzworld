package com.buezman.blondzworld.service.impl;

import com.buezman.blondzworld.entity.Product;
import com.buezman.blondzworld.entity.Role;
import com.buezman.blondzworld.entity.User;
import com.buezman.blondzworld.exception.ResourceNotFoundException;
import com.buezman.blondzworld.repository.ProductRepository;
import com.buezman.blondzworld.repository.RoleRepository;
import com.buezman.blondzworld.repository.UserRepository;
import com.buezman.blondzworld.request.ProductRequest;
import com.buezman.blondzworld.request.UserRequest;
import com.buezman.blondzworld.response.ProductResponse;
import com.buezman.blondzworld.response.UserResponse;
import com.buezman.blondzworld.service.AdminService;
import com.buezman.blondzworld.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AuthService authService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final CloudinaryService cloudinaryService;
    private final ProductRepository productRepository;

    @Override
    public UserResponse registerStaff(UserRequest userRequest) {

        authService.validateAdmin();

        Role role = roleRepository.findRoleByName(userRequest.getRole())
                .orElseThrow(()-> new ResourceNotFoundException("Role", "name", userRequest.getRole()));

        User user = modelMapper.map(userRequest, User.class);
        user.setPassword(passwordEncoder.encode(generatePassword()));
        user.setRoles(Collections.singleton(role));
        userRepository.save(user);

        return modelMapper.map(user, UserResponse.class);
    }

    @Override
    public ProductResponse addProduct(ProductRequest productRequest) throws IOException {
        authService.validateAdmin();
        String imageUrl = productRequest.getImageFile() == null ?
                null : cloudinaryService.uploadImage(productRequest.getImageFile());
        Product product = modelMapper.map(productRequest, Product.class);
        product.setImageUrl(imageUrl);
        productRepository.save(product);

        return modelMapper.map(product, ProductResponse.class);
    }

    private String generatePassword() {
        int length = 7;
        boolean useLetters = true;
        boolean useNumbers = true;
        String password = RandomStringUtils.random(length, useLetters, useNumbers);
        log.info(password);
        return password;
    }

}
