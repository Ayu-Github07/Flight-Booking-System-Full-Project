package com.example.airportservice.repository;

import java.util.List;

import com.example.airportservice.model.Airport;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

//Defining airportRepo interface which is extending mongorepostory which is extended from JPA Repositories
@Repository
public interface AirportRepo extends MongoRepository<Airport, Long> {

    // To find Airport using airport id
    public Airport findByAirportId(long airportId);

    // To get all the airports by city name
    public List<Airport> findByAirportCity(String airportCity);

    // To get all the airports by airport name
    public List<Airport> findByAirportName(String airportName);

    // To find all the airports with airport name and city name
    public Airport findByAirportNameAndAirportCity(String airportName, String airportCity);
}
