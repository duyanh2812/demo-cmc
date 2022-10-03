/**
 * @mbg.generated generator on Fri Aug 26 13:33:47 GMT+07:00 2022
 */
package com.example.cart.service.impl;

import com.example.cart.dao.CartMapper;
import com.example.cart.dto.CartDetailRequestDto;
import com.example.cart.dto.ProductRequestDto;
import com.example.cart.service.CartService;
import com.example.product.model.Cart;
import com.example.product.model.CartDetail;
import com.example.product.model.Image;
import com.example.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartMapper cartMapper;
    @Override
    public void updateCart(CartDetailRequestDto request) {
        // check exists cart
        Long cartId = null;
        Cart cart = cartMapper.getCartByUserId(request.getUserid());
        if (cart == null) {
            Cart newCart = new Cart();
            newCart.setUserId(request.getUserid());
            cartId = cartMapper.insertCart(newCart);
        }else {
            cartId = cart.getId();
        }
        cartMapper.deleteCartByCartId(cartId);
        for (ProductRequestDto item : request.getProductRequestList()){
            CartDetail cartDetail = new CartDetail();
            cartDetail.setCartId(cartId);
            cartDetail.setProductId(item.getId());
            cartDetail.setQuantity(item.getCount());
            cartMapper.insertCartDetail(cartDetail);
        }
    }

    @Override
    public List<Product> getCartByUserId(Long userId) {
        List<Product> productList = cartMapper.getProductUserId(userId);
        for (Product product : productList){
            List<Image> images = cartMapper.getImageListByProductId(product.getId());
            product.setListImg(images);
        }
        return productList;
    }
}