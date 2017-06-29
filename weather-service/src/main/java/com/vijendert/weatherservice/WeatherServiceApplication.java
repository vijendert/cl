package com.vijendert.weatherservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.servo.jsr166e.ThreadLocalRandom;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
public class WeatherServiceApplication {
	
	private String[] weather={"sunny","rainy","cloudy","windy"};

	public static void main(String[] args) {
		SpringApplication.run(WeatherServiceApplication.class, args);
	}
	@GetMapping("/weather")
	public String theWeatherToday()
	{
		int random = ThreadLocalRandom.current().nextInt(0, 4);
		return weather[random];
	}
}
