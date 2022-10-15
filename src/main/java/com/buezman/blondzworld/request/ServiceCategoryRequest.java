package com.buezman.blondzworld.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceCategoryRequest {
    private String name;
    private String description;
    private MultipartFile imageFile;
}
