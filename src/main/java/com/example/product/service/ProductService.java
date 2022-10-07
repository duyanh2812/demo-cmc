/**
* @mbg.generated
* generator on Mon Aug 29 08:55:05 GMT+07:00 2022
*/
package com.example.product.service;


import com.example.product.model.Product;

import java.util.List;

public interface ProductService {
    int deleteByPrimaryKey(Long id);

    int insert(Product row);

    Product selectByPrimaryKey(Long id);
    Product latestCreatedProduct();

    List<Product> selectAll(int current_page, int page_size);
    
    int countAll();

    int updateByPrimaryKey(Product row);

    List<Product> search(String name, int current_page, int page_size);
}