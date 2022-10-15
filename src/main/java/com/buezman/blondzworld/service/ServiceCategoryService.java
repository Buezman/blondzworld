package com.buezman.blondzworld.service;

import com.buezman.blondzworld.entity.ServiceCategory;
import com.buezman.blondzworld.request.ServiceCategoryRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ServiceCategoryService {
    ServiceCategory createServiceCategory(ServiceCategoryRequest request) throws IOException;
    String deleteServiceCategory(Long categoryId);

}
