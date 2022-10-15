package com.buezman.blondzworld.service.impl;

import com.buezman.blondzworld.entity.ProductCategory;
import com.buezman.blondzworld.entity.User;
import com.buezman.blondzworld.enums.UserRole;
import com.buezman.blondzworld.exception.AppApiException;
import com.buezman.blondzworld.exception.ResourceNotFoundException;
import com.buezman.blondzworld.repository.ProductCategoryRepository;
import com.buezman.blondzworld.service.AuthService;
import com.buezman.blondzworld.service.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private final AuthService authService;
    private final ProductCategoryRepository productCategoryRepository;


    @Override
    public ProductCategory createProductCategory(ProductCategory productCategory) {
        authService.validateAdmin();
        productCategoryRepository.save(productCategory);

        return productCategory;
    }

    @Override
    public String deleteProductCategory(Long categoryId) {
        authService.validateAdmin();
        ProductCategory productCategory = productCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Product Category", "ID", ""+categoryId));
        productCategoryRepository.delete(productCategory);

        return String.format("'%s' category deleted successfully", productCategory.getName());
    }
}
