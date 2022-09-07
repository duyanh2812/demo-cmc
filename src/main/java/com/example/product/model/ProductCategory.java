package com.example.product.model;

import lombok.Data;

import java.util.Date;

@Data
public class ProductCategory {
    private Long categoryId;

    private Long productId;

    private Date createdDtm;

    private Long createdId;

    private Date updatedDtm;

    private Long updatedId;
}