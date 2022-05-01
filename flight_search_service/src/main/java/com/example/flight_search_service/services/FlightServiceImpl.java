package com.example.flight_search_service.services;

import java.time.LocalDate;
import java.util.List;

import com.example.flight_search_service.classes.Airport;
import com.example.flight_search_service.classes.FlightDetails;
import com.example.flight_search_service.model.Flight;
import com.example.flight_search_service.repository.FlightDetailsRepo;
import com.example.flight_search_service.repository.FlightRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepo flightRepo;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private FlightDetailsRepo flightDetailsRepo;

    @Override
    public List<Flight> getAllFlights() {

        return flightRepo.findAll();
    }

    @Override
    public Flight setFlight(Flight flight) {

        System.out.println("Service method set flight is working properly");

        long sourceCityId = flight.getSourceCityId();
        long destinationCityId = flight.getDestinationCityId();

        System.out.println("Source Airport Id is" + sourceCityId);
        System.out.println("Destination Airport Id is" + destinationCityId);

        if (sourceCityId != destinationCityId) {
            Airport sourceAirport = restTemplate.getForObject("http://AIRPORT-SERVICE/api/airports/" + sourceCityId,
                    Airport.class);

            Airport destinationAirport = restTemplate.getForObject(
                    "http://AIRPORT-SERVICE/api/airports/" + destinationCityId,
                    Airport.class);

            System.out.println("Source Airport " + sourceAirport);
            System.out.println("Destination Airport " + destinationAirport);

            System.out.println("Departure Date: " + flight.getDepartureDate());
            System.out.println("Arriavl Time: " + flight.getArivalTime());

            FlightDetails flightDetails = new FlightDetails();
            flightDetails.setId(flight.getId());
            flightDetails.setFlightName(flight.getFlightName());
            flightDetails.setSourceAirportCity(sourceAirport.getAirportCity());
            flightDetails.setDestinationAirportCity(destinationAirport.getAirportCity());
            flightDetails.setDepartureDate(flight.getDepartureDate());
            flightDetails.setArivalTime(flight.getArivalTime());
            flightDetails.setSeats(flight.getSeats());
            flightDetails.setFare(flight.getFare());
            flightDetailsRepo.save(flightDetails);

        }
        System.out.println("Flight Details has been added successfully from service layer");
        return flightRepo.save(flight);
    }

    @Override
    public Flight getFlightById(int id) {

        return flightRepo.findById(id);
    }

    @Override
    public List<FlightDetails> getAllFlightDetails() {

        return flightDetailsRepo.findAll();
    }

    @Override
    public List<FlightDetails> getFlightsBetweenSourceAndDestination(String sourceCity, String destinationCity) {

        return flightDetailsRepo.findBySourceAirportCityAndDestinationAirportCity(sourceCity, destinationCity);
    }

    @Override
    public Flight updateFlight(Flight flight) {

        Flight flights = flightRepo.findById(flight.getId());

        if (flights != null) {
            setFlight(flight);
        }

        return flight;
    }

    @Override
    public void deleteFlight(long id) {

        flightRepo.deleteById(id);
        flightDetailsRepo.deleteById(id);

    }

    @Override
    public List<FlightDetails> getFlightBwSourceAndDestinationAndDepartureDate(String sourceCity,
            String destinationCity, LocalDate departureDate) {

        return flightDetailsRepo.findBySourceAirportCityAndDestinationAirportCityAndDepartureDate(sourceCity,
                destinationCity, departureDate);
    }

    @Override
    public FlightDetails getFlightDetailsById(long flightId) {

        return flightDetailsRepo.findById(flightId).get();
    }

}
