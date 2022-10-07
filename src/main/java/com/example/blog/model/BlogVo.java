package com.example.blog.model;

import java.util.Date;
import java.util.List;

import com.example.common.BaseVo;
import com.example.product.model.Category;
import com.example.product.model.Image;

import lombok.Data;

@Data
public class BlogVo extends BaseVo{
	private String id;
	private String userName;
	private String title;
	private String url;
	private String description;
	private String delYn;
	private Date createdDtm;
	private Long createdId;
	private Date updatedDtm;
	private Long updatedId;
	private List<Image> images;
	private List<Category> categories;
}
