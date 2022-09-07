package com.example.product.dto;

import org.springframework.web.multipart.MultipartFile;

public class ProductAddRequestDTO {
    private String displayName;
    private Double price;
    private String description;
    private MultipartFile[] images;
    private String[] categories;
}
