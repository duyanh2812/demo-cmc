package com.example.blog.controller;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.dto.BlogDto;
import com.example.blog.model.BlogVo;
import com.example.blog.service.BlogService;
import com.example.common.ResponseVo;

@RestController
@RequestMapping(value = "/api/")
@CrossOrigin("*")
public class BlogController {
	
	@Autowired
	BlogService blogService;

    @GetMapping(value = "/blogs")
    public ResponseEntity<List<BlogVo>> getAllBlogsUser(@RequestParam(value = "page") int currentPage,
                                             @RequestParam(value = "size") int pageSize, @RequestParam BlogDto blogDto){
        return new ResponseEntity<>(blogService.getAllBlog(blogDto),HttpStatus.OK);
    }
    
    @GetMapping(value = "/admin/blogs")
    public ResponseEntity<List<BlogVo>> getAllBlogsAdmin(@RequestParam(value = "page") int currentPage,
                                             @RequestParam(value = "size") int pageSize, @RequestParam BlogDto blogDto){
        return new ResponseEntity<>(blogService.getAllBlog(blogDto),HttpStatus.OK);
    }
    
    @GetMapping(value = "/blog/{id}")
    public ResponseEntity<BlogVo> getAllProductsUser(@PathVariable(value = "id") BigInteger blogId){
        return new ResponseEntity<>(blogService.getBlogById(blogId),HttpStatus.OK);
    }
    
    @PostMapping(value="/admin/blog/add")
    public ResponseEntity<ResponseVo> insertBlog(BlogVo input){
    	blogService.insertBlog(input);
    	return new ResponseEntity<>(new ResponseVo("Created"), HttpStatus.CREATED);
    }
}
