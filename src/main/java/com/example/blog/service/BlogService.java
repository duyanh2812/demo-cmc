/**
* @mbg.generated
* generator on Mon Aug 29 08:55:05 GMT+07:00 2022
*/
package com.example.blog.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.blog.dto.BlogDto;
import com.example.blog.model.BlogVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface BlogService {
	
	int insertBlog(MultipartFile[] multipartFile, String jsonFile, Long userId) throws JsonMappingException, JsonProcessingException;
	List<BlogVo> getAllBlog(BlogDto input, int page, int size);
	int countAll(BlogDto input);
	BlogVo	getBlogById(String blogId);
	int deleteBlogById(String blogId);
	int updateBlogById(BlogDto input);
}