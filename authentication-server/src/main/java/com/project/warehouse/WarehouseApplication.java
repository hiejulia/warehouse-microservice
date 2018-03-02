package com.project.warehouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;

@EnableIntegration
@EnableEurekaClient
@SpringBootApplication
@IntegrationComponentScan
public class WarehouseApplication {

	public static void main(String[] args) {
		SpringApplication.run(WarehouseApplication.class, args);
	}
}
