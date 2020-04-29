package com.atguigu.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PAYMENT-HYSTRIX-SERVICE", fallback = PaymentHystricServiceImplFallback.class)
public interface PaymentHystrixService {
	@GetMapping("/payment/hystrix/ok/{id}")
	public String paymentOK(@PathVariable("id") Integer id);

	@GetMapping("/payment/hystrix/timeout/{id}")
	public String payment(@PathVariable("id") Integer id);
}
