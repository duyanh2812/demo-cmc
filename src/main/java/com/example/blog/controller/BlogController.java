package com.example.blog.controller;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.multipart.MultipartFile;

import com.example.blog.dto.BlogDto;
import com.example.blog.model.BlogVo;
import com.example.blog.service.BlogService;
import com.example.common.ResponseVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@RequestMapping(value = "/api/")
@CrossOrigin("*")
public class BlogController {
	
	@Autowired
	BlogService blogService;

    @GetMapping(value = "/blogs")
    public ResponseEntity<ResponseVo> getAllBlogsUser(@RequestParam(value = "page") int currentPage,
                                             @RequestParam(value = "size") int pageSize, @RequestParam(required = false) BlogDto blogDto){
    	ResponseVo resVo = new ResponseVo("OK");
    	resVo.setVoList(blogService.getAllBlog(blogDto, currentPage, pageSize));
    	resVo.setTotal(blogService.countAll(blogDto));
        return new ResponseEntity<>(resVo,HttpStatus.OK);
    }
    
    @GetMapping(value = "/admin/blogs")
    public ResponseEntity<ResponseVo> getAllBlogsAdmin(@RequestParam(value = "page") int currentPage,
                                             @RequestParam(value = "size") int pageSize, @RequestParam(required = false) BlogDto blogDto){
    	ResponseVo resVo = new ResponseVo("OK");
    	resVo.setVoList(blogService.getAllBlog(blogDto, currentPage, pageSize));
    	resVo.setTotal(blogService.countAll(blogDto));
        return new ResponseEntity<>(resVo,HttpStatus.OK);
    }
    
    @GetMapping(value = "/blog/{id}")
    public ResponseEntity<BlogVo> getAllProductsUser(@PathVariable(value = "id") String blogId){
        return new ResponseEntity<>(blogService.getBlogById(blogId),HttpStatus.OK);
    }
    
    @PostMapping(value="/admin/blog/add")
    public ResponseEntity<ResponseVo> insertBlog(@RequestParam(value = "files", required = false) MultipartFile[] multipartFile,
            String jsonFile, HttpServletRequest request) throws JsonMappingException, JsonProcessingException{
    	blogService.insertBlog(multipartFile, jsonFile, (Long) request.getSession().getAttribute("userId"));
    	return new ResponseEntity<>(new ResponseVo("Created"), HttpStatus.CREATED);
    }
}
