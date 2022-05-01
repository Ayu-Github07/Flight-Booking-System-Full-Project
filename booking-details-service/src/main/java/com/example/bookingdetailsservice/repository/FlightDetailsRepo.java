package com.example.bookingdetailsservice.repository;

import com.example.bookingdetailsservice.classes.FlightDetails;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface FlightDetailsRepo extends MongoRepository<FlightDetails, Long> {

}
