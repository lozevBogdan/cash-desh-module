package com.cashdesh.cashdesh.module.dto;

public class ResponceDto {

	private String msg;
	private Integer code;

	public ResponceDto(String msg, Integer code) {
		super();
		this.msg = msg;
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

}
