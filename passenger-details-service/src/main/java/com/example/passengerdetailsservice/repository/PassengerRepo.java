package com.example.passengerdetailsservice.repository;

import com.example.passengerdetailsservice.model.Passenger;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRepo extends MongoRepository<Passenger, Long> {

    public Passenger findByPassengerId(long passengerId);
}
