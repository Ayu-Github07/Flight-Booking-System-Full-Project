package com.example.flight_search_service.repository;

import java.time.LocalDate;
import java.util.List;

import com.example.flight_search_service.classes.FlightDetails;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightDetailsRepo extends MongoRepository<FlightDetails, Long> {

    public List<FlightDetails> findBySourceAirportCityAndDestinationAirportCity(String sourceAirportCity,
            String destinationAirportCity);

    @Query("{ 'sourceAirportCity': ?0, 'destinationAirportCity': ?1, 'departureDate': ?2}")
    public List<FlightDetails> findBySourceAirportCityAndDestinationAirportCityAndDepartureDate(
            String sourceAirportCity, String destinationAirportCity, LocalDate departureDate);
}
