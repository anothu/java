package com.atguigu.apringcloud.controller;

import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.atguigu.apringcloud.service.PaymentService;

@RestController
public class PaymentController {
	@Resource
	private PaymentService paymentService;
	@Value("S{server.port}")
	private String serverPort;

	@GetMapping("/payment/hystrix/ok/{id}")
	public String paymentOK(@PathVariable("id") Integer id) {
		String result = paymentService.paymentOK(id);
		System.err.println("******result:  " + result);
		return result;
	}

	@GetMapping("/payment/hystrix/timeout/{id}")
	public String payment(@PathVariable("id") Integer id) {
		String result = paymentService.payment(id);
		System.err.println("******result:  " + result);
		return result;
	}

	// 服务熔断
	@GetMapping("/payment/circuit/{id}")
	public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
		String result = paymentService.paumentCircuitBreaker(id);
		System.err.println("*****result:" + result);
		return result;
	}
}
