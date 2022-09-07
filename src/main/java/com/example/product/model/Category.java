package com.example.product.model;

import lombok.Data;

import java.util.Date;

@Data
public class Category {
    private Long id;

    private String name;

    private String url;

    private String desc;

    private Date createdDtm;

    private Long createdId;

    private Date updatedDtm;

    private Long updatedId;
}