package com.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@SpringBootApplication
@EnableCircuitBreaker
@EnableFeignClients
public class OrderApplication {

	/**
	 * 通过restTemplate调用其他服务
	 * @return
	 */
	@Bean
	@LoadBalanced
	RestTemplate restTemplate(){
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}

}
