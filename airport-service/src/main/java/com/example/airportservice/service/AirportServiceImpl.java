package com.example.airportservice.service;

import java.util.List;

import com.example.airportservice.model.Airport;
import com.example.airportservice.repository.AirportRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirportServiceImpl implements AirportService {

    @Autowired
    private AirportRepo airportRepo;

    @Override
    public List<Airport> getAllAirports() {

        return airportRepo.findAll();
    }

    @Override
    public Airport getAirportByAirportId(long airportId) {

        return airportRepo.findByAirportId(airportId);
    }

    @Override
    public List<Airport> getAirportByAirportName(String airportName) {

        return airportRepo.findByAirportName(airportName);
    }

    @Override
    public List<Airport> getAirportByAirportModel(String airportCity) {

        return airportRepo.findByAirportCity(airportCity);
    }

    @Override
    public List<Airport> setAllAirports(List<Airport> airports) {

        return airportRepo.saveAll(airports);
    }

    @Override
    public Airport setAirportDetails(Airport airport) {

        return airportRepo.save(airport);
    }

    @Override
    public Airport updateAirportByAirportId(Airport airport) {

        return airportRepo.save(airport);
    }

    @Override
    public void deleteAirportByAirportId(long airportId) {

        airportRepo.deleteById(airportId);
    }

}
