package com.atguigu.springcloud.entities;

public class CommonResult<T> {
	public CommonResult() {

	}

	public CommonResult(Integer code, String message) {
		this.setCode(code);
		this.message = message;
		this.data = null;
	}

	public CommonResult(Integer code, String message, T data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	private Integer code;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	private String message;
	private T data;

}
