package com.example.flight_search_service.classes;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "airports")
public class Airport {

    @Id
    private long airportId;
    private String airportName;
    private String airportCity;

    public Airport() {
        super();
    }

    public Airport(long airportId, String airportName, String airportCity) {
        this.airportId = airportId;
        this.airportName = airportName;
        this.airportCity = airportCity;
    }

    public long getAirportId() {
        return airportId;
    }

    public void setAirportId(long airportId) {
        this.airportId = airportId;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public String getAirportCity() {
        return airportCity;
    }

    public void setAirportCity(String airportCity) {
        this.airportCity = airportCity;
    }

    @Override
    public String toString() {
        return "Airport [airportCity=" + airportCity + ", airportId=" + airportId + ", airportName=" + airportName
                + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((airportCity == null) ? 0 : airportCity.hashCode());
        result = prime * result + (int) (airportId ^ (airportId >>> 32));
        result = prime * result + ((airportName == null) ? 0 : airportName.hashCode());
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
        Airport other = (Airport) obj;
        if (airportCity == null) {
            if (other.airportCity != null)
                return false;
        } else if (!airportCity.equals(other.airportCity))
            return false;
        if (airportId != other.airportId)
            return false;
        if (airportName == null) {
            if (other.airportName != null)
                return false;
        } else if (!airportName.equals(other.airportName))
            return false;
        return true;
    }

}