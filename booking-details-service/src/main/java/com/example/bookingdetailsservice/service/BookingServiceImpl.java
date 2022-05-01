package com.example.bookingdetailsservice.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;

import com.example.bookingdetailsservice.classes.CheckIn;
import com.example.bookingdetailsservice.classes.FlightDetails;
import com.example.bookingdetailsservice.classes.Passenger;
import com.example.bookingdetailsservice.model.Booking;
import com.example.bookingdetailsservice.repository.BookingRepo;
import com.example.bookingdetailsservice.repository.CheckInRepo;
import com.example.bookingdetailsservice.repository.FlightDetailsRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepo bookingRepo;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CheckInRepo checkInRepo;

    @Autowired
    private FlightDetailsRepo flightDetailsRepo;

    @Autowired
    private SequenceGeneratorService service;

    @Override
    public List<Booking> getAllBookings() {

        return bookingRepo.findAll();
    }

    @Override
    public List<Booking> getAllBookingByUsername(String username) {

        return bookingRepo.findByUsername(username);
    }

    @Override
    public Booking setBookingDetails(Booking booking) {

        if (booking != null) {
            char[] chars = new char[] {
                    'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
                    'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
                    'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2',
                    '3', '4', '5', '6', '7', '8', '9' };

            Random rnd = new Random();
            int charslength = chars.length;
            int passLenght = 10;
            String ticketGenerated = new String();

            for (int i = 0; i < passLenght; i++) {
                int index = rnd.nextInt(charslength - i - 1);
                char a = chars[i + index];
                chars[i + index] = chars[i];
                chars[i] = a;
                ticketGenerated += a;
            }

            booking.setTicketId(ticketGenerated);

            LocalDate date = LocalDate.now();
            booking.setBookingDate(date);

            LocalTime time = LocalTime.now();
            booking.setBookingTime(time);

            List<CheckIn> checkIns = setCheckInDetails(booking);

            System.out.println("CheckIn Details");
            System.out.println(checkIns);

            bookingRepo.save(booking);
        }

        return booking;
    }

    public List<CheckIn> setCheckInDetails(Booking booking) {

        long flightId = booking.getFlightId();

        System.out.println("This is working properly!");

        FlightDetails flightDetails = restTemplate
                .getForObject("http://localhost:8001/api/flights/flightDetails/" + flightId, FlightDetails.class);

        System.out.println("Flight Details are: \n" + flightDetails.toString());

        int countPassengers = booking.getPassengers().size();

        System.out.println("Number of passengers are: " + countPassengers);

        flightDetails.setSeats(flightDetails.getSeats() - countPassengers);

        flightDetailsRepo.save(flightDetails);

        for (Passenger p : booking.getPassengers()) {

            CheckIn checkIn = new CheckIn();
            checkIn.setId(service.getSequenceNumber(Booking.SEQUENCE_NAME));
            checkIn.setFlightId(flightId);
            String name = p.getFirstName().toString() + " " + p.getLastName().toString();
            checkIn.setPassengerName(name);
            checkIn.setPassengerAge(p.getAge());
            checkIn.setPassengerGender(p.getGender());
            checkIn.setSource(flightDetails.getSourceAirportCity());
            checkIn.setDestination(flightDetails.getDestinationAirportCity());
            checkIn.setDeparturDate(flightDetails.getDepartureDate());
            checkIn.setCheckedIn(false);
            checkIn.setUsername(booking.getUsername());
            checkIn.setTicketId(booking.getTicketId());

            System.out.println("Check in details are: \n" + checkIn.toString());

            checkInRepo.save(checkIn);
        }

        return checkInRepo.findByTicketId(booking.getTicketId());
    }

    public List<CheckIn> getCheckInDetailsByTicketId(String ticketId) {
        return checkInRepo.findByTicketId(ticketId);
    }

    public List<CheckIn> getCheckInDetailsByCheckIn(long flightId, LocalDate departurDate) {
        return checkInRepo.findByFlightIdAndDeparturDate(flightId, departurDate);
    }

    public List<CheckIn> getCheckInDetailsByUsername(String username) {
        return checkInRepo.findByUsername(username);
    }

    @Override
    public Booking updateBookingById(Booking booking) {

        return bookingRepo.save(booking);
    }

    @Override
    public void deletBookingByUsername(String ticketId) {

        Booking booking = getBookingByTicketId(ticketId);

        FlightDetails flightDetails = restTemplate.getForObject(
                "http://localhost:8001/api/flights/flightDetails/" + booking.getFlightId(), FlightDetails.class);

        System.out.println("Flight Details are: \n" + flightDetails.toString());

        flightDetails.setSeats(flightDetails.getSeats() + booking.getPassengers().size());

        flightDetailsRepo.save(flightDetails);

        checkInRepo.deleteByTicketId(ticketId);
        bookingRepo.deleteByTicketId(ticketId);

    }

    @Override
    public Booking getBookingByTicketId(String ticketId) {

        return bookingRepo.findByTicketId(ticketId);
    }

}
