package com.vijendert.ribbontimeapp;

import javax.inject.Inject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.vijendert.ribbontimeapp.service.RibbonTimeAppService;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
@EnableCircuitBreaker
// @RibbonClient(name = "time-service", configuration = RibbonTimeConfig.class)
public class RibbonTimeAppApplication {

	@Inject
	RibbonTimeAppService ribbonTimeAppService;

	public static void main(String[] args) {
		SpringApplication.run(RibbonTimeAppApplication.class, args);
	}

	@GetMapping
	public String getResponse() {
		return ribbonTimeAppService.getCurrentTime();
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
