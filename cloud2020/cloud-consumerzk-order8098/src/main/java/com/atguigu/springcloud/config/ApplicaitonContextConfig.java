package com.atguigu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicaitonContextConfig {

	@Bean
	@LoadBalanced
	public RestTemplate getreRestTemplate() {
		return new RestTemplate();
	}
}
