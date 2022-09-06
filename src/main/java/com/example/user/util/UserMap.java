package com.example.user.util;

import com.example.user.dto.req.ReqUserDto;
import com.example.user.dto.resp.RespUserDto;
import com.example.user.model.User;

public class UserMap {
    public static User DtoMapUser(ReqUserDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        return user;
    }

    public static RespUserDto UserMapDto(User user) {
        RespUserDto dto = new RespUserDto();
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        return dto;
    }
}
