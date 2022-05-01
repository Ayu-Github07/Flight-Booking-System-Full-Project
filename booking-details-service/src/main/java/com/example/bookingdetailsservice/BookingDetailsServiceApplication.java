package com.example.bookingdetailsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BookingDetailsServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(BookingDetailsServiceApplication.class, args);
	}

}
