package com.example.bookingdetailsservice.controller;

import java.time.LocalDate;
import java.util.List;

import com.example.bookingdetailsservice.classes.CheckIn;
import com.example.bookingdetailsservice.exception.TicketAlreadyBookedException;
import com.example.bookingdetailsservice.model.Booking;
import com.example.bookingdetailsservice.repository.CheckInRepo;
import com.example.bookingdetailsservice.service.BookingServiceImpl;
import com.example.bookingdetailsservice.service.SequenceGeneratorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "http://localhost:4200")
public class BookingController {

    @Autowired
    private BookingServiceImpl bookingServiceImpl;

    @Autowired
    private SequenceGeneratorService service;

    @Autowired
    private CheckInRepo checkInRepo;

    @GetMapping("/")
    public ResponseEntity<List<Booking>> getAllBookings() {

        try {
            return ResponseEntity.ok().body(bookingServiceImpl.getAllBookings());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @GetMapping("/getByUsername")
    public ResponseEntity<List<Booking>> getBookingByUsername(@RequestParam("username") String username) {
        try {
            return ResponseEntity.ok().body(bookingServiceImpl.getAllBookingByUsername(username));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<String> setBookingDetails(@RequestBody Booking booking) {

        try {
            if (booking.getTicketId() != null) {
                throw new TicketAlreadyBookedException("You have already booked the ticket");
            } else {
                booking.setBookingId(service.getSequenceNumber(Booking.SEQUENCE_NAME));
                bookingServiceImpl.setBookingDetails(booking);

                return ResponseEntity.ok().body(booking.getTicketId());
            }

        } catch (TicketAlreadyBookedException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Booking Details Cannot Be Null!!");
        }

    }

    @GetMapping("/getByTicketId/{ticketId}")
    public ResponseEntity<Booking> getBookingByTicketId(@PathVariable("ticketId") String ticketId) {
        try {
            return ResponseEntity.ok().body(bookingServiceImpl.getBookingByTicketId(ticketId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/getCheckInDetails")
    public ResponseEntity<List<CheckIn>> getCheckIn(@RequestParam("flightId") long flightId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departurDate) {
        try {
            return ResponseEntity.ok().body(bookingServiceImpl.getCheckInDetailsByCheckIn(flightId, departurDate));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/getCheckInDetails/ticketId")
    public ResponseEntity<List<CheckIn>> getCheckInDetailsByTickedId(@RequestParam("ticketId") String ticketId) {
        try {
            return ResponseEntity.ok().body(bookingServiceImpl.getCheckInDetailsByTicketId(ticketId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/getCheckInDetails/username")
    public ResponseEntity<List<CheckIn>> getCheckInDetailsByUsername(@RequestParam("username") String username) {

        try {
            return ResponseEntity.ok().body(bookingServiceImpl.getCheckInDetailsByUsername(username));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @DeleteMapping("/bookingDetails")
    public ResponseEntity<String> deleteTicketByTicketId(@RequestParam("ticketId") String ticketId) {
        try {

            List<CheckIn> checkIn = checkInRepo.findByTicketId(ticketId);

            for (CheckIn c : checkIn) {
                if (c.isCheckedIn()) {
                    throw new AlreadyCheckedInException();
                }
            }

            bookingServiceImpl.deletBookingByUsername(ticketId);

            return ResponseEntity.ok().body("Deleted Successfully!");
        } catch (AlreadyCheckedInException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("User has already checked in cannot cancel the ticket!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
