package com.example.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponseDto {
    private static final long serialVersionUID = 1L;
    private final String token;
}
