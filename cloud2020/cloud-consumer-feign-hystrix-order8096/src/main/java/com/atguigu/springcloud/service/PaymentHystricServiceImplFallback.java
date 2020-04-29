package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentHystricServiceImplFallback implements PaymentHystrixService {

	@Override
	public String paymentOK(Integer id) {
		// TODO Auto-generated method stub
		return "通过实现PaymentHystrixService接口完成fallback";
	}

	@Override
	public String payment(Integer id) {
		// TODO Auto-generated method stub
		return "通过实现PaymentHystrixService接口完成fallback";
	}

}
