package com.atguigu.apringcloud.service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import cn.hutool.Hutool;
import cn.hutool.core.util.IdUtil;

@Service
public class PaymentService {
	// 正常方法
	public String paymentOK(Integer id) {

		return "线程池：  " + Thread.currentThread().getName() + "  getInfoCorrectly,id:  " + id;
	}

	// 模拟出错
	@HystrixCommand(fallbackMethod = "paymentTimeoutHandler", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000") })
	public String payment(Integer id) {
		// int a=10/0;
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "线程池：  " + Thread.currentThread().getName() + "  getInfoTimeOut,id:  " + id;
	}

	// 兜底方法
	public String paymentTimeoutHandler(Integer id) {
		return "线程池(兜底)：  " + Thread.currentThread().getName() + "  getInfoTimeOut,id:  " + id;
	}

	// ===服务熔断===10次访问失败超过6次就会跳闸，10秒钟后重新尝试
	@HystrixCommand(fallbackMethod = "paymentCircuitBreaker_Fallback", commandProperties = {
			@HystrixProperty(name = "circuitBreaker.enabled", value = "true"), // 是否开启断路器
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), // 请求次数
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), // 时间窗口期
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"), }// 失败率达到多少后跳闸
	)
	public String paumentCircuitBreaker(@PathVariable("id") Integer id) {
		if (id < 0) {
			throw new RuntimeException("id不能小于0");
		}
		String serialNumber = IdUtil.fastSimpleUUID();
		return Thread.currentThread().getName() + "\t" + "调用成功，流水号是" + serialNumber;
	}

	public String paymentCircuitBreaker_Fallback(@PathVariable("id") Integer id) {
		return "id不能为负数哦>w<,id是" + id;
	}

}
