package com.maddy.user.config;

import org.modelmapper.ModelMapper;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/*
 * @LoadBalanced : Instead of using localhost:port, we can use Service Name.
 */

@Configuration
public class UserConfig {

	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
