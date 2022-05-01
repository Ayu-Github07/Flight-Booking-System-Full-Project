package com.example.flight_search_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FlightSearchServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightSearchServiceApplication.class, args);
	}

}
