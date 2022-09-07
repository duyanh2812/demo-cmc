package com.example.product.dao;

import com.example.product.model.ProductCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductCategoryMapper {
    int deleteByPrimaryKey(@Param("categoryId") Long categoryId, @Param("productId") Long productId);

    int insert(long categoryId, long productId);

    ProductCategory selectByPrimaryKey(@Param("categoryId") Long categoryId, @Param("productId") Long productId);

    List<ProductCategory> selectAll();

    int updateByPrimaryKey(ProductCategory row);
}