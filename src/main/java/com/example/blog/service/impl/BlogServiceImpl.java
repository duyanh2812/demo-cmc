package com.example.blog.service.impl;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog.dto.BlogDto;
import com.example.blog.mapper.BlogMapper;
import com.example.blog.model.BlogVo;
import com.example.blog.service.BlogService;

@Service
public class BlogServiceImpl implements BlogService{
	
	@Autowired
	BlogMapper blogMapper;

	@Override
	public int insertBlog(BlogVo blog, Long userId) {
		// TODO Auto-generated method stub
		blog.setCreatedId(userId);
		blogMapper.insertBlog(blog);
		return 0;
	}

	@Override
	public List<BlogVo> getAllBlog(BlogDto input, int currentPage, int size) {
		// TODO Auto-generated method stub
		return blogMapper.getBlogs(input, currentPage, size);
	}

	@Override
	public BlogVo getBlogById(Long blogId) {
		// TODO Auto-generated method stub
		return blogMapper.getBlogById(blogId);
	}

	@Override
	public int deleteBlogById(Long blogId) {
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
