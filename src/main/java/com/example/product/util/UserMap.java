package com.example.product.util;


import com.example.product.dto.UserDTO;
import com.example.product.model.User;

public class UserMap {


    public static UserDTO mapUser(User user){
        if(user.getId() != null) {
            UserDTO dto = new UserDTO(user.getId(), user.getName(), user.getEmail());
            return dto;
        }
        else
            return new UserDTO();
    }
}
