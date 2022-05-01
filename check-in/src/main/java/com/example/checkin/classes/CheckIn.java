package com.example.checkin.classes;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "check-in")
public class CheckIn {

    @Id
    private long id;
    private long flightId;
    private String ticketId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String username;
    private LocalDate departurDate;
    private String passengerName;
    private int passengerAge;
    private String passengerGender;
    private String source;
    private String destination;
    private boolean isCheckedIn;

    public CheckIn() {
    }

    public CheckIn(long id, long flightId, String ticketId, String username, LocalDate departurDate,
            String passengerName, int passengerAge, String passengerGender, String source, String destination,
            boolean isCheckedIn) {
        this.id = id;
        this.flightId = flightId;
        this.ticketId = ticketId;
        this.username = username;
        this.departurDate = departurDate;
        this.passengerName = passengerName;
        this.passengerAge = passengerAge;
        this.passengerGender = passengerGender;
        this.source = source;
        this.destination = destination;
        this.isCheckedIn = isCheckedIn;
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

    public boolean isCheckedIn() {
        return isCheckedIn;
    }

    public void setCheckedIn(boolean isCheckedIn) {
        this.isCheckedIn = isCheckedIn;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "CheckIn [departurDate=" + departurDate + ", destination=" + destination + ", flightId=" + flightId
                + ", id=" + id + ", isCheckedIn=" + isCheckedIn + ", passengerAge=" + passengerAge
                + ", passengerGender=" + passengerGender + ", passengerName=" + passengerName + ", source=" + source
                + ", ticketId=" + ticketId + ", username=" + username + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((departurDate == null) ? 0 : departurDate.hashCode());
        result = prime * result + ((destination == null) ? 0 : destination.hashCode());
        result = prime * result + (int) (flightId ^ (flightId >>> 32));
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + (isCheckedIn ? 1231 : 1237);
        result = prime * result + passengerAge;
        result = prime * result + ((passengerGender == null) ? 0 : passengerGender.hashCode());
        result = prime * result + ((passengerName == null) ? 0 : passengerName.hashCode());
        result = prime * result + ((source == null) ? 0 : source.hashCode());
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
        CheckIn other = (CheckIn) obj;
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
        if (id != other.id)
            return false;
        if (isCheckedIn != other.isCheckedIn)
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

}