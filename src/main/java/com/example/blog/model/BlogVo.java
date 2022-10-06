package com.example.blog.model;

import java.math.BigInteger;
import java.util.Date;

import com.example.common.BaseVo;

import lombok.Data;

@Data
public class BlogVo extends BaseVo{
	private BigInteger id;
	private String title;
	private String url;
	private String description;
	private String delYn;
	private Date createdDtm;
	private Long createdId;
	private Date updatedDtm;
	private BigInteger updatedId;
}
