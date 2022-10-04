package com.example.blog.model;

import java.math.BigInteger;
import java.util.Date;

import lombok.Data;

@Data
public class BlogVo {
	private BigInteger id;
	private String userId;
	private String title;
	private String description;
	private String delYn;
	private Date createdDtm;
	private BigInteger createdId;
	private Date updatedDtm;
	private BigInteger updatedId;
}