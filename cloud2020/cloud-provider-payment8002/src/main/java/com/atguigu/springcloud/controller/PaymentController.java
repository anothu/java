package com.atguigu.springcloud.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;

@RestController
public class PaymentController {
	@Resource
	private PaymentService paymentService;

	@Value("${server.port}")
	private String serverPort;

	@PostMapping("/payment/create")
//	@ResponseBody
	public CommonResult create(@RequestBody Payment payment) {
		int result = paymentService.create(payment);
		System.err.println("*****结果是" + result);
		return result > 0 ? new CommonResult(200, "插入数据成功,server-port是" + serverPort, result)
				: new CommonResult(444, "插入数据失败");
	}

	@GetMapping("/payment/get/{id}")
//	@ResponseBody
	public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
		Payment payment = paymentService.getPaymentById(id);
		System.err.println("**********查询结果@@@@@@@@" + payment);
		return payment != null ? new CommonResult<Payment>(200, "查询数据成功,server-port是" + serverPort, payment)
				: new CommonResult<Payment>(444, "查询数据失败,查询id： " + id, null);
	}

}
