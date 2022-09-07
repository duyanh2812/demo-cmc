package com.example.cart.dao;

import com.example.product.model.Cart;
import com.example.product.model.CartDetail;
import com.example.product.model.Image;
import com.example.product.model.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CartMapper {

    @Insert("INSERT INTO cart (user_id)" +
            "VALUES (#{userId})")
    long insertCart(Cart row);
    @Insert("INSERT INTO cart_detail (cart_id, product_id, quantity)" +
            "VALUES (#{cartId}, #{productId}, #{quantity})")
    long insertCartDetail(CartDetail detail);
    @Select("SELECT p.id, p.name, p.desc, p.price, ct.quantity\n" +
            "FROM cart_detail ct\n" +
            "INNER JOIN cart c on ct.cart_id = c.id\n" +
            "INNER JOIN product p on ct.product_id = p.id\n" +
            "WHERE c.user_id = #{userId}")
    @Results(value = {
            @Result(property = "name", column = "name")
            , @Result(property = "id", column = "id")
            , @Result(property = "desc", column = "desc")
            , @Result(property = "price", column = "price")
            , @Result(property = "quantity", column = "quantity")})
    List<Product> getProductUserId(Long userId);

    @Select("SELECT url from image where product_id = #{productId}")
    @Results(value = {
            @Result(property = "url", column = "url")})
    List<Image> getImageListByProductId(Long productId);

    @Delete("delete from cart_detail" +
            " where cart_id = #{cartId}")
    void deleteCartByCartId(long cartId);

    @Select("SELECT * from cart WHERE user_id = #{userId}")
    @Results(value = {
            @Result(property = "id", column = "id")
            , @Result(property = "userId", column = "user_id")})
    Cart getCartByUserId(Long userId);
}