package com.tss.employee_service.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {

	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
//	@Bean
//	RestTemplate restTemplate() {
//		return new RestTemplate();
//	}
	
	@Bean
	WebClient webClient() {
		return WebClient.builder().build();
	}
	
	
	
}
