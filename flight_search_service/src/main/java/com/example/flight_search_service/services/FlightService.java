package com.example.flight_search_service.services;

import java.time.LocalDate;
import java.util.List;

import com.example.flight_search_service.classes.FlightDetails;
import com.example.flight_search_service.model.Flight;

public interface FlightService {

    public List<Flight> getAllFlights();

    public Flight setFlight(Flight flight);

    public Flight getFlightById(int id);

    public List<FlightDetails> getAllFlightDetails();

    public List<FlightDetails> getFlightsBetweenSourceAndDestination(String sourceCity, String destinationCity);

    public List<FlightDetails> getFlightBwSourceAndDestinationAndDepartureDate(String sourceCity,
            String destinationCity, LocalDate departureDate);

    public Flight updateFlight(Flight flight);

    public void deleteFlight(long id);

    public FlightDetails getFlightDetailsById(long flightId);
}
