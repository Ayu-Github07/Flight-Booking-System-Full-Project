package com.example.checkin.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.example.checkin.classes.Booking;
import com.example.checkin.classes.FlightDetails;
import com.example.checkin.classes.Ticket;
import com.example.checkin.repository.CheckInDetailsRepo;
import com.example.checkin.repository.TicketRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CheckInServiceImpl implements CheckInService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TicketRepo ticketRepo;

    @Autowired
    private CheckInDetailsService checkInDetailsService;

    @Autowired
    private CheckInDetailsRepo checkInDetailsRepo;

    @Override
    public List<Ticket> getSeatNumbersCheckIn(String ticketId) {

        if (ticketId != null) {

            Booking booking = restTemplate.getForObject("http://localhost:8006/api/bookings/getByTicketId/" + ticketId,
                    Booking.class);
            System.out.println("Booking Details are:  \n" + booking.toString());

            FlightDetails flight = restTemplate.getForObject(
                    "http://localhost:8001/api/flights/flightDetails/" + booking.getFlightId(), FlightDetails.class);

            System.out.println("Flight Details:\n " + flight);

            int totalPassengers = booking.getPassengers().size();
            System.out.println("Total Number of Passengers: " + totalPassengers);

            List<String> seatsAllocated = new ArrayList<>();

            com.example.checkin.classes.CheckIn[] checkIns = checkInDetailsService
                    .getAllCheckInByTicketId(ticketId);

            for (com.example.checkin.classes.CheckIn c : checkIns) {

                if (!c.isCheckedIn()) {

                    c.setCheckedIn(true);
                    String ticket = generateSeatNumber();

                    checkInDetailsRepo.save(c);

                    Ticket ticket2 = new Ticket();
                    ticket2.setTicketId(ticketId);
                    ticket2.setPassengerName(c.getPassengerName());
                    ticket2.setPassengerAge(c.getPassengerAge());
                    ticket2.setPassengerGender(c.getPassengerGender());
                    ticket2.setDeparturDate(c.getDeparturDate());
                    ticket2.setSource(c.getSource());
                    ticket2.setFlightId(c.getFlightId());
                    ticket2.setUsername(c.getUsername());
                    ticket2.setDestination(c.getDestination());
                    ticket2.setTicket(ticket);

                    ticketRepo.save(ticket2);
                } else {
                    Ticket ticket = new Ticket();
                    ticket.setTicket("");
                }

            }

            System.out.println("Seats Allocated Are: " + seatsAllocated);

        }
        return ticketRepo.findByTicketId(ticketId);
    }

    public String generateSeatNumber() {
        char[] chars = new char[] {
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
                'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        char[] nums = new char[] {
                '0', '1', '2',
                '3', '4', '5', '6', '7', '8', '9'
        };

        Random rnd = new Random();
        int charslength = chars.length;
        String seatGenerated = new String();

        int randomChar = rnd.nextInt(charslength);
        seatGenerated += chars[randomChar];

        for (int i = 1; i <= 2; i++) {
            int randomNum = rnd.nextInt(nums.length);
            seatGenerated += nums[randomNum];
        }

        return seatGenerated;
    }

    public List<Ticket> getTicketByTicketId(String ticketId) {
        return ticketRepo.findByTicketId(ticketId);
    }

    public List<Ticket> getTicketByFlightId(long flightId) {
        return ticketRepo.findByFlightId(flightId);
    }

    public List<Ticket> getTicketByFlightIdAndDeparturDate(long flightId, LocalDate departurDate) {
        return ticketRepo.findByFlightIdAndDeparturDate(flightId, departurDate);
    }

    public Ticket getTicketByTicketIdAndPassengerName(String ticketId, String passengerName) {
        return ticketRepo.findByTicketIdAndPassengerName(ticketId, passengerName);
    }
}
