package com.vijendert.configclientapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
@RefreshScope
public class ConfigClientAppApplication {
	
	@Autowired
	private ConfigClientAppConfiguration configClientAppConfiguration;
	
	@Value("${some.other.property}")
	private String someOtherProperty;

	public static void main(String[] args) {
		SpringApplication.run(ConfigClientAppApplication.class, args);
	}
	
	@RequestMapping
	public String theResponse()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(configClientAppConfiguration.getProperty());
		sb.append(" || ");
		sb.append(someOtherProperty);
		return sb.toString();
	}
}
