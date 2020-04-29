package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OpenFiegnMain8097 {
	public static void main(String[] args) {
		SpringApplication.run(OpenFiegnMain8097.class, args);
	}
}
