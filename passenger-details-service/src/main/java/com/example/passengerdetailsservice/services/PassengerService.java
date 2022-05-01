package com.example.passengerdetailsservice.services;

import java.util.List;

import com.example.passengerdetailsservice.model.Passenger;

public interface PassengerService {

    public List<Passenger> getAllPassengersDetails();

    public Passenger getPassengerByPassengerId(long passengerId);

    public void setAllPassengerDetails(List<Passenger> passengers);

    public void setPassengerDetails(Passenger passenger);

    public Passenger updatePassengerDetails(Passenger passenger);

    public void deletePassengerDetailsById(long passengerId);
}
