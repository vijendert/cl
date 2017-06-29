package com.vijendert.ribbontimeapp.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class RibbonTimeAppService {

	@Inject
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "unknown")
	public String getCurrentTime() {
		return restTemplate.getForEntity("http://time-service", String.class).getBody();
	}

	public String unknown() {
		return "unknkown";
	}

}
