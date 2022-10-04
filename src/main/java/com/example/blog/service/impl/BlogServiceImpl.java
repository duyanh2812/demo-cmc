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
	public int insertBlog(BlogVo blog) {
		// TODO Auto-generated method stub
		blogMapper.insertBlog(blog);
		return 0;
	}

	@Override
	public List<BlogVo> getAllBlog(BlogDto input) {
		// TODO Auto-generated method stub
		return blogMapper.getBlogs(input);
	}

	@Override
	public BlogVo getBlogById(BigInteger blogId) {
		// TODO Auto-generated method stub
		return blogMapper.getBlogById(blogId);
	}

	@Override
	public int deleteBlogById(BigInteger blogId) {
		// TODO Auto-generated method stub
		return blogMapper.deleteBlogById(blogId);
	}

	@Override
	public int updateBlogById(BlogDto input) {
		// TODO Auto-generated method stub
		return blogMapper.updateBlogById(input);
	}

}
