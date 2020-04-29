package com.atguigu.springcloud.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
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

	@Resource
	private DiscoveryClient discoveryClient;

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

	@GetMapping("/payment/discovery")
	public Object discovery() {
		// 所有服务
		List<String> services = discoveryClient.getServices();
		for (String s : services) {
			System.err.println("***service***" + s);
		}
		// 各种实例
		List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
		for (ServiceInstance s : instances) {
			System.err.println("host:" + s.getHost() + " instanceid:" + s.getInstanceId() + " port:" + s.getPort());
		}
		return discoveryClient;
	}

}
