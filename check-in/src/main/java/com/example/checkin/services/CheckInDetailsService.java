package com.example.checkin.services;

import com.example.checkin.classes.CheckIn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CheckInDetailsService {

    @Autowired
    public RestTemplate restTemplate;

    public CheckIn[] getAllCheckInByTicketId(String ticketId) {

        System.out.println("Inside Service is working");
        System.out.println("Ticket id is: " + ticketId);

        ResponseEntity<CheckIn[]> response = restTemplate.getForEntity(
                "http://localhost:8006/api/bookings/getCheckInDetails/ticketId?ticketId=" + ticketId,
                CheckIn[].class);

        System.out.println("Response is here");
        System.out.println(response.getBody());

        CheckIn[] checkIns = response.getBody();

        System.out.println("All The Check In Details!");
        System.out.println(checkIns.toString());

        return checkIns;

    }
}
