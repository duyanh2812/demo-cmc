/**
 * @mbg.generated generator on Fri Aug 26 13:33:47 GMT+07:00 2022
 */
package com.example.cart.service;

import com.example.cart.dto.CartDetailRequestDto;
import com.example.product.model.Product;

import java.util.List;

public interface CartService {


    void updateCart(CartDetailRequestDto request);

    List<Product> getCartByUserId(Long userId);
}