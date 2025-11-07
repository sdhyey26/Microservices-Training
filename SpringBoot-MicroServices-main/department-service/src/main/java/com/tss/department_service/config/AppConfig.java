package com.tss.department_service.config;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {
	
	@Autowired
	private FeignClientInterceptor feignClientInterceptor;
	
	@Bean
	public WebClient webClient()
	{
		return WebClient.builder().build();
	}
	
	@Bean
	public RequestInterceptor requestInterceptor() {
		return feignClientInterceptor;
	}
}
