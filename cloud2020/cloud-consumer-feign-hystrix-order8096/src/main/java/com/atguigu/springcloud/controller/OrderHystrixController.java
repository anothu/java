package com.atguigu.springcloud.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystrixController {

	@Resource
	private PaymentHystrixService paymentHystrixService;

	@GetMapping("/consumer/payment/hystrix/ok/{id}")
	public String paymentOK(@PathVariable("id") Integer id) {
		String result = paymentHystrixService.paymentOK(id);
		// System.err.println("******result: " + result);
		return result;
	}

	@GetMapping("/consumer/payment/hystrix/timeout/{id}")
	@HystrixCommand(fallbackMethod = "paymentTimeout", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500") })
//	@HystrixCommand
	public String payment(@PathVariable("id") Integer id) {
		// int a=10/0;直接出现异常的情况也可以处理
		String result = paymentHystrixService.payment(id);
		// System.err.println("******result: " + result);
		return result;
	}

	// 兜底方法
	public String paymentTimeout(@PathVariable("id") Integer id) {
		return "我是8096的兜底方法，超时或出现异常了！";
	}

	// 下面是全局fallback方法
	public String payment_Global_FallbackMethod() {
		return "global 异常处理信息，稍后再试";
	}

}
