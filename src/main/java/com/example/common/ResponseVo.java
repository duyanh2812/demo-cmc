package com.example.common;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseVo {
	public ResponseVo(String message) {
		this.message = message;
	}
	private String message;
	private int total;
	private List<? extends BaseVo> voList;
	
}
