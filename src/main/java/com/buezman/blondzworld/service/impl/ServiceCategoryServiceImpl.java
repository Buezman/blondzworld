package com.buezman.blondzworld.service.impl;

import com.buezman.blondzworld.entity.ProductCategory;
import com.buezman.blondzworld.entity.ServiceCategory;
import com.buezman.blondzworld.exception.ResourceNotFoundException;
import com.buezman.blondzworld.repository.ProductCategoryRepository;
import com.buezman.blondzworld.repository.ServiceCategoryRepository;
import com.buezman.blondzworld.request.ServiceCategoryRequest;
import com.buezman.blondzworld.service.AuthService;
import com.buezman.blondzworld.service.ServiceCategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor

public class ServiceCategoryServiceImpl implements ServiceCategoryService {

    private final AuthService authService;
    private final CloudinaryService cloudinaryService;
    private final ServiceCategoryRepository serviceCategoryRepository;
    private final ModelMapper modelMapper;


    @Override
    public ServiceCategory createServiceCategory(ServiceCategoryRequest request) throws IOException {
        authService.validateAdmin();
        String imageUrl = request.getImageFile() == null ? null : cloudinaryService.uploadImage(request.getImageFile());
        ServiceCategory category = modelMapper.map(request, ServiceCategory.class);
        category.setImageUrl(imageUrl);
        serviceCategoryRepository.save(category);

        return category;
    }

    @Override
    public String deleteServiceCategory(Long categoryId) {
        authService.validateAdmin();
        ServiceCategory serviceCategory = serviceCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Service Category", "ID", ""+categoryId));
        serviceCategoryRepository.delete(serviceCategory);

        return String.format("'%s' category deleted successfully", serviceCategory.getName());
    }
}
