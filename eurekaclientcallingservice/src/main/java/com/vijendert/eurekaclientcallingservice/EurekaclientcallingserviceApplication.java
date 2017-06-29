package com.vijendert.eurekaclientcallingservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;



@SpringBootApplication
@EnableEurekaClient
@RestController
public class EurekaclientcallingserviceApplication {

	@Autowired
	private RestTemplateBuilder rtb;
	
	@Autowired
	private EurekaClient ec;
	
	public static void main(String[] args) {
		SpringApplication.run(EurekaclientcallingserviceApplication.class, args);
	}
	@RequestMapping("/")
	public String callService()
	{
		InstanceInfo instanceInfo = ec.getNextServerFromEureka("service", false);
		String url = instanceInfo.getHomePageUrl();
		RestTemplate rt = rtb.build();
		ResponseEntity<String> response = rt.exchange(url, HttpMethod.GET, null, String.class);
		return response.getBody();
	}
}
