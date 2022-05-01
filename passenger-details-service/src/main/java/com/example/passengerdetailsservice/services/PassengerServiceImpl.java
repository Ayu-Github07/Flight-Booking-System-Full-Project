package com.example.passengerdetailsservice.services;

import java.util.List;

import com.example.passengerdetailsservice.model.Passenger;
import com.example.passengerdetailsservice.repository.PassengerRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    private PassengerRepo passengerRepo;

    @Override
    public List<Passenger> getAllPassengersDetails() {

        return passengerRepo.findAll();
    }

    @Override
    public Passenger getPassengerByPassengerId(long passengerId) {

        return passengerRepo.findByPassengerId(passengerId);
    }

    @Override
    public void setAllPassengerDetails(List<Passenger> passengers) {

        passengerRepo.saveAll(passengers);
    }

    @Override
    public void setPassengerDetails(Passenger passenger) {

        passengerRepo.save(passenger);
    }

    @Override
    public Passenger updatePassengerDetails(Passenger passenger) {

        return passengerRepo.save(passenger);
    }

    @Override
    public void deletePassengerDetailsById(long passengerId) {

        passengerRepo.deleteById(passengerId);
    }

}
