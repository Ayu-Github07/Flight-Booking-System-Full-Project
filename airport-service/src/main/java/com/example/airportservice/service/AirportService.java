package com.example.airportservice.service;

import java.util.List;

import com.example.airportservice.model.Airport;

//To define all the methods for service layer of airport microservice
//This layer will communicate to airport dao and controller layer to perform middleware operations
public interface AirportService {

    // To get the list of all the airports defined
    public List<Airport> getAllAirports();

    // To get the airport using airport id
    public Airport getAirportByAirportId(long airportId);

    // To get all the list of the airport name using airport name
    public List<Airport> getAirportByAirportName(String airportName);

    // To get all the airport by using airport city name
    public List<Airport> getAirportByAirportModel(String airportCity);

    // To set the details of all the airports in the dao layer
    public List<Airport> setAllAirports(List<Airport> airports);

    // To set the details of only one airport
    public Airport setAirportDetails(Airport airport);

    // To update the details of airport by using airport id
    public Airport updateAirportByAirportId(Airport airport);

    // To delete the details of airport by using airport id
    public void deleteAirportByAirportId(long airportId);

}
