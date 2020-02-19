package com.onlinehubsolutions.DynamicReportEngine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DynamicReportEngineApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(DynamicReportEngineApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(DynamicReportEngineApplication.class, args);
	}

}
