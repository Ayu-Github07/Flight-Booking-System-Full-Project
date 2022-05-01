package com.example.checkin.services;

import java.util.List;

import com.example.checkin.classes.Ticket;

public interface CheckInService {

    public List<Ticket> getSeatNumbersCheckIn(String ticketId);

}
