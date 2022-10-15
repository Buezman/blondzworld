package com.buezman.blondzworld.controller;

import com.buezman.blondzworld.entity.ServiceCategory;
import com.buezman.blondzworld.enums.AppConstants;
import com.buezman.blondzworld.request.ServiceCategoryRequest;
import com.buezman.blondzworld.service.ServiceCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(AppConstants.BASE_URL+"/service-categories")
@RequiredArgsConstructor
public class ServiceCategoryController {

    private final ServiceCategoryService serviceCategoryService;

    @PostMapping(consumes = {"multipart/form-data"})
    @ResponseStatus(HttpStatus.CREATED)
    public ServiceCategory createServiceCategory(@ModelAttribute ServiceCategoryRequest request) throws IOException {
        return serviceCategoryService.createServiceCategory(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteServiceCategory(@PathVariable Long id) {
        return serviceCategoryService.deleteServiceCategory(id);
    }
}
