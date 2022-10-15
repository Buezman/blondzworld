package com.buezman.blondzworld.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {
    private String name;
    private BigDecimal price;
    private String description;
    private MultipartFile imageFile;
}
