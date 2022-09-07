package com.example.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequestDto {
    private static final long serialVersionUID = 2636936156391265891L;
    private String username;
    private String password;
}
