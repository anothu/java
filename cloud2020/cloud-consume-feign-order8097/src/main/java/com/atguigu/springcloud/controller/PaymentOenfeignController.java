package com.atguigu.springcloud.controller;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentOpenfeignService;

@RestController
public class PaymentOenfeignController {
	@Resource
	private PaymentOpenfeignService paymentOpenfeignService;

	@GetMapping("/consumer/payment/get/{id}")
	public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
		return paymentOpenfeignService.getPaymentById(id);
	}
}
