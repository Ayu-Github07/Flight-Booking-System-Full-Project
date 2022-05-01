package com.example.bookingdetailsservice.service;

import java.util.List;

import com.example.bookingdetailsservice.model.Booking;

public interface BookingService {

    public List<Booking> getAllBookings();

    public List<Booking> getAllBookingByUsername(String username);

    public Booking setBookingDetails(Booking booking);

    public Booking updateBookingById(Booking booking);

    public void deletBookingByUsername(String username);

    public Booking getBookingByTicketId(String ticketId);

}
