package com.example.flight_search_service.classes;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "flight-details")
public class FlightDetails {

    @Id
    private long id;
    private String flightName;
    private String sourceAirportCity;
    private String destinationAirportCity;
    private LocalDate departureDate;
    private LocalTime arivalTime;
    private int seats;
    private double fare;

    public FlightDetails() {
    }

    public FlightDetails(long id, String flightName, String sourceAirportCity, String destinationAirportCity,
            LocalDate departureDate, LocalTime arivalTime, int seats, double fare) {
        this.id = id;
        this.flightName = flightName;
        this.sourceAirportCity = sourceAirportCity;
        this.destinationAirportCity = destinationAirportCity;
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

    public String getSourceAirportCity() {
        return sourceAirportCity;
    }

    public void setSourceAirportCity(String sourceAirportCity) {
        this.sourceAirportCity = sourceAirportCity;
    }

    public String getDestinationAirportCity() {
        return destinationAirportCity;
    }

    public void setDestinationAirportCity(String destinationAirportCity) {
        this.destinationAirportCity = destinationAirportCity;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
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
        return "FlightDetails [arivalTime=" + arivalTime + ", departureDate=" + departureDate
                + ", destinationAirportCity=" + destinationAirportCity + ", fare=" + fare + ", flightName=" + flightName
                + ", id=" + id + ", seats=" + seats + ", sourceAirportCity=" + sourceAirportCity + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((arivalTime == null) ? 0 : arivalTime.hashCode());
        result = prime * result + ((departureDate == null) ? 0 : departureDate.hashCode());
        result = prime * result + ((destinationAirportCity == null) ? 0 : destinationAirportCity.hashCode());
        long temp;
        temp = Double.doubleToLongBits(fare);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((flightName == null) ? 0 : flightName.hashCode());
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + seats;
        result = prime * result + ((sourceAirportCity == null) ? 0 : sourceAirportCity.hashCode());
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
        FlightDetails other = (FlightDetails) obj;
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
        if (destinationAirportCity == null) {
            if (other.destinationAirportCity != null)
                return false;
        } else if (!destinationAirportCity.equals(other.destinationAirportCity))
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
        if (sourceAirportCity == null) {
            if (other.sourceAirportCity != null)
                return false;
        } else if (!sourceAirportCity.equals(other.sourceAirportCity))
            return false;
        return true;
    }

}
