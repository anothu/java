package com.atguigu.springcloud.entities;

import java.io.Serializable;

public class Payment implements Serializable {
	public Payment() {
	}

	public Payment(Long id, String serial) {
		this.id = id;
		this.serial = serial;
	}

	private Long id;
	private String serial;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}
}
