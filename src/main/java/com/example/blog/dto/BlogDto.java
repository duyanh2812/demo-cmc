package com.example.blog.dto;

import java.util.List;

import com.example.blog.model.BlogVo;

import lombok.Data;

@Data
public class BlogDto{
	private String title;
	private String description;
	private List<BlogVo> blogVos; 
}
