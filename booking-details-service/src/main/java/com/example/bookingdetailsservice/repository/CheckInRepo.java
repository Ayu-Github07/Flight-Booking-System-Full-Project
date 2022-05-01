package com.example.bookingdetailsservice.repository;

import java.time.LocalDate;
import java.util.List;

import com.example.bookingdetailsservice.classes.CheckIn;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckInRepo extends MongoRepository<CheckIn, Long> {

    public List<CheckIn> findByFlightIdAndDeparturDate(Long flightId, LocalDate departurDate);

    public List<CheckIn> findByTicketId(String ticketId);

    public List<CheckIn> findByUsername(String username);

    public void deleteByTicketId(String ticketId);
}
