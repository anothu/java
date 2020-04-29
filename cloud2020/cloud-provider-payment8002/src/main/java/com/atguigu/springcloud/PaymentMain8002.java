package com.atguigu.springcloud;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.boot.SpringApplication;

@EnableEurekaClient
@SpringBootApplication
public class PaymentMain8002 {
	public static void main(String[] args) {
		SpringApplication.run(PaymentMain8002.class, args);
	}
}
