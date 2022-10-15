package com.buezman.blondzworld.service;

import com.buezman.blondzworld.entity.ProductCategory;

public interface ProductCategoryService {
    ProductCategory createProductCategory(ProductCategory productCategory);
    String deleteProductCategory(Long categoryId);
}
