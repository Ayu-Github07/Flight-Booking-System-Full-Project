package com.example.bookingdetailsservice.exception;

public class TicketAlreadyBookedException extends Exception {

    public TicketAlreadyBookedException(String message) {
        super(message);
    }
}
