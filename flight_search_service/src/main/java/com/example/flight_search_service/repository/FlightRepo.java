package com.example.flight_search_service.repository;

import com.example.flight_search_service.model.Flight;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FlightRepo extends MongoRepository<Flight, Long> {

    public Flight findById(long id);
}
