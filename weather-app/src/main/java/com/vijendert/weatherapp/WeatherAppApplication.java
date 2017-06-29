package com.vijendert.weatherapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.vijendert.weatherapp.services.WeatherAppService;

@SpringBootApplication
@RestController
@EnableCircuitBreaker
@EnableDiscoveryClient
public class WeatherAppApplication {

	@Autowired
	public WeatherAppService weatherAppService;

	public static void main(String[] args) {
		SpringApplication.run(WeatherAppApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@GetMapping("/current/weather")
	public String whatIsWeather() {
		return "The current weather is " + weatherAppService.callWeatherService();
	}
}