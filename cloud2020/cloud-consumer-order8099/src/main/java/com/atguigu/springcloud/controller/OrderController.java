package com.atguigu.springcloud.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;

@RestController
public class OrderController {
	// public static final String PAYMENT_URL = "http://localhost:8001";
	public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";
	@Resource
	private RestTemplate restTemplate;

	// @SuppressWarnings("unchecked")
	@GetMapping("/consumer/payment/create")
	public CommonResult create(Payment payment) {
		return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
	}

	// @SuppressWarnings("unchecked")
	@GetMapping("/consumer/payment/get/{id}")
	public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
		System.out.println("finding...");
		return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
	}
}
