package com.example.checkin.controller;

import java.time.LocalDate;
import java.util.List;

import com.example.checkin.classes.Ticket;
import com.example.checkin.model.CheckIn;
import com.example.checkin.repository.CheckInDetailsRepo;
import com.example.checkin.services.CheckInServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/checkin")
@CrossOrigin(origins = "http://localhost:4200")
public class CheckInController {

    @Autowired
    private CheckInServiceImpl checkInServiceImpl;

    @Autowired
    private CheckInDetailsRepo checkInDetailsRepo;

    @PostMapping("/ticketDetails")
    public ResponseEntity<List<Ticket>> getTicketByTicketId(@RequestBody CheckIn checkIn) {

        try {
            String ticketId = checkIn.getTicketId();

            List<com.example.checkin.classes.CheckIn> checks = checkInDetailsRepo.findByTicketId(ticketId);
            boolean isPresent = false;
            for (com.example.checkin.classes.CheckIn c : checks) {
                if (!c.isCheckedIn()) {
                    return ResponseEntity.ok().body(checkInServiceImpl.getSeatNumbersCheckIn(ticketId));
                }
                if (c.getTicketId().equals(checkIn.getTicketId())) {
                    isPresent = true;
                }
            }

            if (!isPresent) {
                throw new IncorretTicketId();
            }
            throw new Exception();
        } catch (IncorretTicketId e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        }

    }

    @GetMapping("/getSeatsByTicketId")
    public ResponseEntity<List<Ticket>> getTicketsByTicketId(@RequestParam("ticketId") String ticketId) {

        try {
            return ResponseEntity.ok().body(checkInServiceImpl.getTicketByTicketId(ticketId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @GetMapping("/getTicketsByFlight")
    public ResponseEntity<List<Ticket>> getTicketByFlightId(@RequestParam("flightId") long flightId) {
        try {
            return ResponseEntity.ok().body(checkInServiceImpl.getTicketByFlightId(flightId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/getTicketByFlightAndDeparturDate")
    public ResponseEntity<List<Ticket>> getTickets(@RequestParam("flightId") long flightId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departurDate) {
        try {
            return ResponseEntity.ok()
                    .body(checkInServiceImpl.getTicketByFlightIdAndDeparturDate(flightId, departurDate));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/getTicketByIdAndPassengerName")
    public ResponseEntity<Ticket> getTicket(@RequestParam("ticketId") String ticketId,
            @RequestParam("passengerName") String passengerName) {
        try {
            return ResponseEntity.ok()
                    .body(checkInServiceImpl.getTicketByTicketIdAndPassengerName(ticketId, passengerName));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
