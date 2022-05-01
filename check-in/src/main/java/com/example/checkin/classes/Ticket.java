package com.example.checkin.classes;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tickets")
public class Ticket {

    private String ticketId;
    private long flightId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate departurDate;
    private String passengerName;
    private int passengerAge;
    private String passengerGender;
    private String source;
    private String username;
    private String destination;
    private String ticket;

    public Ticket() {
    }

    public Ticket(String ticketId, long flightId, LocalDate departurDate, String passengerName, int passengerAge,
            String passengerGender, String source, String username, String destination, String ticket) {
        this.ticketId = ticketId;
        this.flightId = flightId;
        this.departurDate = departurDate;
        this.passengerName = passengerName;
        this.passengerAge = passengerAge;
        this.passengerGender = passengerGender;
        this.source = source;
        this.username = username;
        this.destination = destination;
        this.ticket = ticket;
    }

    public long getFlightId() {
        return flightId;
    }

    public void setFlightId(long flightId) {
        this.flightId = flightId;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public LocalDate getDeparturDate() {
        return departurDate;
    }

    public void setDeparturDate(LocalDate departurDate) {
        this.departurDate = departurDate;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public int getPassengerAge() {
        return passengerAge;
    }

    public void setPassengerAge(int passengerAge) {
        this.passengerAge = passengerAge;
    }

    public String getPassengerGender() {
        return passengerGender;
    }

    public void setPassengerGender(String passengerGender) {
        this.passengerGender = passengerGender;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((departurDate == null) ? 0 : departurDate.hashCode());
        result = prime * result + ((destination == null) ? 0 : destination.hashCode());
        result = prime * result + (int) (flightId ^ (flightId >>> 32));
        result = prime * result + passengerAge;
        result = prime * result + ((passengerGender == null) ? 0 : passengerGender.hashCode());
        result = prime * result + ((passengerName == null) ? 0 : passengerName.hashCode());
        result = prime * result + ((source == null) ? 0 : source.hashCode());
        result = prime * result + ((ticket == null) ? 0 : ticket.hashCode());
        result = prime * result + ((ticketId == null) ? 0 : ticketId.hashCode());
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
        Ticket other = (Ticket) obj;
        if (departurDate == null) {
            if (other.departurDate != null)
                return false;
        } else if (!departurDate.equals(other.departurDate))
            return false;
        if (destination == null) {
            if (other.destination != null)
                return false;
        } else if (!destination.equals(other.destination))
            return false;
        if (flightId != other.flightId)
            return false;
        if (passengerAge != other.passengerAge)
            return false;
        if (passengerGender == null) {
            if (other.passengerGender != null)
                return false;
        } else if (!passengerGender.equals(other.passengerGender))
            return false;
        if (passengerName == null) {
            if (other.passengerName != null)
                return false;
        } else if (!passengerName.equals(other.passengerName))
            return false;
        if (source == null) {
            if (other.source != null)
                return false;
        } else if (!source.equals(other.source))
            return false;
        if (ticket == null) {
            if (other.ticket != null)
                return false;
        } else if (!ticket.equals(other.ticket))
            return false;
        if (ticketId == null) {
            if (other.ticketId != null)
                return false;
        } else if (!ticketId.equals(other.ticketId))
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Ticket [departurDate=" + departurDate + ", destination=" + destination + ", flightId=" + flightId
                + ", passengerAge=" + passengerAge + ", passengerGender=" + passengerGender + ", passengerName="
                + passengerName + ", source=" + source + ", ticket=" + ticket + ", ticketId=" + ticketId + ", username="
                + username + "]";
    }

}
