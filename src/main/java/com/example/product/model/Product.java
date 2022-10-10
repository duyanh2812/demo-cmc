package com.example.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

import com.example.common.BaseVo;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Product extends BaseVo{
    private Long id;

    private String name;

    private Double price;

    private String desc;

    private char deleteYn = 'N';

    private Date createdDtm;

    private Long createdId;

    private Date updatedDtm;

    private Long updatedId;

    private List<Image> listImg;

    private List<Category> listCategory;

    private Integer quantity;

    public Product(String name, Double price, String desc) {
        this.name = name;
        this.price = price;
        this.desc = desc;

    }
}