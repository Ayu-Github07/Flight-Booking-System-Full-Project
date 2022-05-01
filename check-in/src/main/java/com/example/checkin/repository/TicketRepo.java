package com.example.checkin.repository;

import java.time.LocalDate;
import java.util.List;

import com.example.checkin.classes.Ticket;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TicketRepo extends MongoRepository<Ticket, Long> {

    public List<Ticket> findByTicketId(String ticketId);

    public List<Ticket> findByFlightId(long flightId);

    public List<Ticket> findByFlightIdAndDeparturDate(long flightId, LocalDate departurDate);

    public Ticket findByTicketIdAndPassengerName(String ticketId, String passengerName);
}
