package com.buezman.blondzworld.controller;

import com.buezman.blondzworld.entity.ProductCategory;
import com.buezman.blondzworld.enums.AppConstants;
import com.buezman.blondzworld.service.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping(AppConstants.BASE_URL+"/product-categories")
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ProductCategory createProductCategory(@RequestBody ProductCategory productCategory) {
        return productCategoryService.createProductCategory(productCategory);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteProductCategory(@PathVariable Long id) {
        return productCategoryService.deleteProductCategory(id);
    }
}
