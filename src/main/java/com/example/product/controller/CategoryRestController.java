package com.example.product.controller;

import com.example.product.dto.CategoryMapper;
import com.example.product.model.Category;
import com.example.product.service.CategoryService;
import com.example.product.util.CategoryMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class CategoryRestController {

//    CategoryMap categoryMap = new CategoryMap();

    @Autowired
    CategoryService categoryService;
    @GetMapping(value = "/categories")
    public ResponseEntity getAllCategories(){
        try {
            List<Category> list = categoryService.selectAll();
            List<CategoryMapper> listDTO = CategoryMap.mapCategory(list);
            return  new ResponseEntity<>(listDTO, HttpStatus.OK);
        }
        catch (Exception e){
             return  new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }
    }
}
