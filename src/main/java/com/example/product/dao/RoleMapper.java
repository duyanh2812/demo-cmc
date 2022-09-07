package com.example.product.dao;

import com.example.product.model.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {
    int deleteByPrimaryKey(Byte id);

    int insert(Role row);

    Role selectByPrimaryKey(Byte id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role row);
}


