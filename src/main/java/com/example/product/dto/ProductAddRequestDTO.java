package com.example.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductAddRequestDTO {
    private String displayName;
    private Double price;
    private String description;
//    private MultipartFile[] images;
    private long[] categories;
}
