/**
* @mbg.generated
* generator on Mon Aug 29 08:55:05 GMT+07:00 2022
*/
package com.example.blog.service;

import java.math.BigInteger;
import java.util.List;

import com.example.blog.dto.BlogDto;
import com.example.blog.model.BlogVo;

public interface BlogService {
	
	int insertBlog(BlogVo blog);
	List<BlogVo> getAllBlog(BlogDto input);
	BlogVo	getBlogById(BigInteger blogId);
	int deleteBlogById(BigInteger blogId);
	int updateBlogById(BlogDto input);
   
}