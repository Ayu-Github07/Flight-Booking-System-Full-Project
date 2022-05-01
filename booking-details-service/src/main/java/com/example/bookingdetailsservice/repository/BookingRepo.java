package com.example.bookingdetailsservice.repository;

import java.util.List;

import com.example.bookingdetailsservice.model.Booking;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepo extends MongoRepository<Booking, Long> {

    public List<Booking> findByUsername(String username);

    public Booking findByTicketId(String ticketId);

    public void deleteByTicketId(String ticketId);

}
