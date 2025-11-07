package com.tss.employee_service.config;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.tss.employee_service.service.DepartmentApiClient;

@Configuration
public class AppConfig {

	@Autowired
	private FeignClientInterceptor feignClientInterceptor;

//    @Bean
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }
	
	@Bean
    WebClient webClient() {
        return WebClient.builder().build();
    }
    
    @Bean
	public RequestInterceptor requestInterceptor() {
		return feignClientInterceptor;
	}

}
