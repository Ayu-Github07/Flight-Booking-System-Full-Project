package com.example.bookingdetailsservice.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.example.bookingdetailsservice.classes.Passenger;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "bookings")
public class Booking {

    @Transient
    public static final String SEQUENCE_NAME = "user_sequence";

    @Id
    private long bookingId;
    private String ticketId;
    private String username;
    private long flightId;
    private List<Passenger> passengers;
    private LocalDate bookingDate;
    private LocalTime bookingTime;
    private double totalFare;

    public Booking() {
        super();
    }

    public Booking(String ticketId, String username, long flightId, List<Passenger> passengers, LocalDate bookingDate,
            LocalTime bookingTime, double totalFare) {
        this.ticketId = ticketId;
        this.username = username;
        this.flightId = flightId;
        this.passengers = passengers;
        this.bookingDate = bookingDate;
        this.bookingTime = bookingTime;
        this.totalFare = totalFare;
    }

    public long getBookingId() {
        return bookingId;
    }

    public void setBookingId(long bookingId) {
        this.bookingId = bookingId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getFlightId() {
        return flightId;
    }

    public void setFlightId(long flightId) {
        this.flightId = flightId;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public LocalTime getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(LocalTime bookingTime) {
        this.bookingTime = bookingTime;
    }

    public double getTotalFare() {
        return totalFare;
    }

    public void setTotalFare(double totalFare) {
        this.totalFare = totalFare;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    @Override
    public String toString() {
        return "Booking [bookingDate=" + bookingDate + ", bookingTime=" + bookingTime + ", flightId=" + flightId
                + ", passengers=" + passengers + ", ticketId=" + ticketId + ", totalFare=" + totalFare + ", username="
                + username + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bookingDate == null) ? 0 : bookingDate.hashCode());
        result = prime * result + ((bookingTime == null) ? 0 : bookingTime.hashCode());
        result = prime * result + (int) (flightId ^ (flightId >>> 32));
        result = prime * result + ((passengers == null) ? 0 : passengers.hashCode());
        result = prime * result + ((ticketId == null) ? 0 : ticketId.hashCode());
        long temp;
        temp = Double.doubleToLongBits(totalFare);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Booking other = (Booking) obj;
        if (bookingDate == null) {
            if (other.bookingDate != null)
                return false;
        } else if (!bookingDate.equals(other.bookingDate))
            return false;
        if (bookingTime == null) {
            if (other.bookingTime != null)
                return false;
        } else if (!bookingTime.equals(other.bookingTime))
            return false;
        if (flightId != other.flightId)
            return false;
        if (passengers == null) {
            if (other.passengers != null)
                return false;
        } else if (!passengers.equals(other.passengers))
            return false;
        if (ticketId == null) {
            if (other.ticketId != null)
                return false;
        } else if (!ticketId.equals(other.ticketId))
            return false;
        if (Double.doubleToLongBits(totalFare) != Double.doubleToLongBits(other.totalFare))
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        return true;
    }

}
