package com.example.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartDetailResponseDto {
    private long cartId;
    private long productId;
    private Date createDate;
}
