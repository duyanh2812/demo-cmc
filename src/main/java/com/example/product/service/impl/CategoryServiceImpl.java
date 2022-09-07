/**
 * @mbg.generated generator on Tue Aug 30 10:47:31 GMT+07:00 2022
 */
package com.example.product.service.impl;

import com.example.product.dao.CategoryMapper;
import com.example.product.model.Category;
import com.example.product.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return categoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Category row) {
        return categoryMapper.insert(row);
    }

    @Override
    public Category selectByPrimaryKey(Long id) {
        return categoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Category> selectAll() {
        return categoryMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Category row) {
        return categoryMapper.updateByPrimaryKey(row);
    }
}