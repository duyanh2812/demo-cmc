package com.example.blog.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.blog.dto.BlogDto;
import com.example.blog.mapper.BlogMapper;
import com.example.blog.model.BlogVo;
import com.example.blog.service.BlogService;
import com.example.file.FileStorageService;
import com.example.product.model.Category;
import com.example.product.model.Image;
import com.example.product.service.ImageService;
import com.example.product.service.ProductCategoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class BlogServiceImpl implements BlogService{
	
	@Autowired
	BlogMapper blogMapper;
	@Autowired
    private FileStorageService fileStorageService;
	@Autowired
    ImageService imageService;
    @Autowired
    ProductCategoryService productCategoryService;

	@Override
	public int insertBlog(MultipartFile[] multipartFile, String jsonFile, Long userId) throws JsonMappingException, JsonProcessingException {
		// TODO Auto-generated method stub
		List<String> listAddedImg = new ArrayList<>();
		for(MultipartFile file: multipartFile){
            String name =    fileStorageService.storeFile(file);
               listAddedImg.add(name);
           }
		String blogID = "BLOG_" + UUID.randomUUID().toString();
		BlogVo blogVo = new ObjectMapper().readValue(jsonFile, BlogVo.class);
		blogVo.setCreatedId(userId);
		blogVo.setId(blogID);
		blogMapper.insertBlog(blogVo);
		for(String name: listAddedImg){
            Image newImg = new Image(null, name, blogID);
            imageService.insert(newImg);
        }
		for(Category cate: blogVo.getCategories()){
          productCategoryService.insert(cate.getId(), null, blogID);
      }
		return 0;
	}

	@Override
	public List<BlogVo> getAllBlog(BlogDto input, int currentPage, int size) {
		// TODO Auto-generated method stub
		List<BlogVo> blogs = blogMapper.getBlogs(input, currentPage - 1, size);
		for (BlogVo blogVo : blogs) {
			blogVo.setImages(blogMapper.getImagesByBlogId(blogVo.getId()));
			blogVo.setCategories(blogMapper.getCategoriesByBlogId(blogVo.getId()));
		}
		return blogs;
	}

	@Override
	public BlogVo getBlogById(String blogId) {
		// TODO Auto-generated method stub
		return blogMapper.getBlogById(blogId);
	}

	@Override
	public int deleteBlogById(String blogId) {
		// TODO Auto-generated method stub
		return blogMapper.deleteBlogById(blogId);
	}

	@Override
	public int updateBlogById(BlogDto input) {
		// TODO Auto-generated method stub
		return blogMapper.updateBlogById(input);
	}

	@Override
	public int countAll(BlogDto input) {
		// TODO Auto-generated method stub
		return blogMapper.countAllBlogs(input);
	}

}
