package com.example.passengerdetailsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PassengerDetailsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PassengerDetailsServiceApplication.class, args);
	}

}
