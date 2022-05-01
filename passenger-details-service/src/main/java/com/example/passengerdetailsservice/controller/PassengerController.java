package com.example.passengerdetailsservice.controller;

import java.util.List;

import com.example.passengerdetailsservice.model.Passenger;
import com.example.passengerdetailsservice.services.PassengerServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/passengers")
public class PassengerController {

    @Autowired
    private PassengerServiceImpl passengerServiceImpl;

    @GetMapping("/")
    public ResponseEntity<List<Passenger>> getAllPassengerDetails() {

        try {
            return ResponseEntity.ok().body(passengerServiceImpl.getAllPassengersDetails());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Passenger> getPassengerDetailsById(@PathVariable("id") long passengerId) {
        try {
            return ResponseEntity.ok().body(passengerServiceImpl.getPassengerByPassengerId(passengerId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<String> setAllPassengers(@RequestBody List<Passenger> passengers) {
        try {
            passengerServiceImpl.setAllPassengerDetails(passengers);
            return ResponseEntity.ok().body("All the passengers details are added successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Warning: Passenger Details could n't be added!");
        }
    }

    @PostMapping("/add-passenger")
    public ResponseEntity<String> setPassengers(@RequestBody Passenger passenger) {
        try {
            passengerServiceImpl.setPassengerDetails(passenger);
            return ResponseEntity.ok().body("Passengers details are added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Warning: Passenger Details could n't be added!");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updatePassengerById(@PathVariable("id") long passengerId,
            @RequestBody Passenger passenger) {
        try {
            List<Passenger> passengers = passengerServiceImpl.getAllPassengersDetails();

            for (Passenger p : passengers) {
                if (p.getPassengerId() == passengerId) {
                    passengerServiceImpl.updatePassengerDetails(passenger);
                    return ResponseEntity.ok().body("Passenger Details Updated Sucessfully");
                }
            }
            throw new Exception();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Warning: No such passenger details exists!");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePassengerById(@PathVariable("id") long passengerId) {
        try {
            List<Passenger> passengers = passengerServiceImpl.getAllPassengersDetails();

            for (Passenger p : passengers) {
                if (p.getPassengerId() == passengerId) {
                    passengerServiceImpl.deletePassengerDetailsById(passengerId);
                    return ResponseEntity.ok().body("Passenger Details Deleted Sucessfully");
                }
            }
            throw new Exception();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Warning: No such passenger details exists!");
        }
    }
}
