package com.example.flight_search_service.model;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "flights")
public class Flight {

    @Id
    private long id;
    private String flightName;
    private long sourceCityId;
    private long destinationCityId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate departureDate;
    @JsonFormat(shape = Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime arivalTime;
    private int seats;
    private double fare;

    public Flight() {
    }

    public Flight(long id, String flightName, long sourceCityId, long destinationCityId, LocalDate departureDate,
            LocalTime arivalTime, int seats, double fare) {
        this.id = id;
        this.flightName = flightName;
        this.sourceCityId = sourceCityId;
        this.destinationCityId = destinationCityId;
        this.departureDate = departureDate;
        this.arivalTime = arivalTime;
        this.seats = seats;
        this.fare = fare;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFlightName() {
        return flightName;
    }

    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }

    public long getSourceCityId() {
        return sourceCityId;
    }

    public void setSourceCityId(long sourceCityId) {
        this.sourceCityId = sourceCityId;
    }

    public long getDestinationCityId() {
        return destinationCityId;
    }

    public void setDestinationCityId(long destinationCityId) {
        this.destinationCityId = destinationCityId;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departurDate) {
        this.departureDate = departurDate;
    }

    public LocalTime getArivalTime() {
        return arivalTime;
    }

    public void setArivalTime(LocalTime arivalTime) {
        this.arivalTime = arivalTime;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    @Override
    public String toString() {
        return "Flight [arivalTime=" + arivalTime + ", departureDate=" + departureDate + ", destinationCityId="
                + destinationCityId + ", fare=" + fare + ", flightName=" + flightName + ", id=" + id + ", seats="
                + seats + ", sourceCityId=" + sourceCityId + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((arivalTime == null) ? 0 : arivalTime.hashCode());
        result = prime * result + ((departureDate == null) ? 0 : departureDate.hashCode());
        result = prime * result + (int) (destinationCityId ^ (destinationCityId >>> 32));
        long temp;
        temp = Double.doubleToLongBits(fare);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((flightName == null) ? 0 : flightName.hashCode());
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + seats;
        result = prime * result + (int) (sourceCityId ^ (sourceCityId >>> 32));
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
        Flight other = (Flight) obj;
        if (arivalTime == null) {
            if (other.arivalTime != null)
                return false;
        } else if (!arivalTime.equals(other.arivalTime))
            return false;
        if (departureDate == null) {
            if (other.departureDate != null)
                return false;
        } else if (!departureDate.equals(other.departureDate))
            return false;
        if (destinationCityId != other.destinationCityId)
            return false;
        if (Double.doubleToLongBits(fare) != Double.doubleToLongBits(other.fare))
            return false;
        if (flightName == null) {
            if (other.flightName != null)
                return false;
        } else if (!flightName.equals(other.flightName))
            return false;
        if (id != other.id)
            return false;
        if (seats != other.seats)
            return false;
        if (sourceCityId != other.sourceCityId)
            return false;
        return true;
    }

}
