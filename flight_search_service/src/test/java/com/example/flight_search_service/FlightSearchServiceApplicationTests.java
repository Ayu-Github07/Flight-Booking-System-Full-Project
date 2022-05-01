package com.example.flight_search_service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.example.flight_search_service.model.Flight;
import com.example.flight_search_service.repository.FlightDetailsRepo;
import com.example.flight_search_service.repository.FlightRepo;
import com.example.flight_search_service.services.FlightServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class FlightSearchServiceApplicationTests {

	List<Flight> flightList = new ArrayList<>();

	Flight flight;

	@Autowired
	private FlightServiceImpl flightServiceImpl;

	@MockBean
	private FlightRepo flightRepo;

	@MockBean
	private FlightDetailsRepo flightDetailsRepo;

	@Test
	void contextLoads() {
	}

	@BeforeEach
	protected void setFlight() {

		flight = new Flight();
		flight.setId(1);
		flight.setFlightName("Jet Airways");
		flight.setSourceCityId(1);
		flight.setDestinationCityId(2);
		flight.setDepartureDate(LocalDate.parse("2022-04-13"));
		flight.setArivalTime(LocalTime.parse("23:00:00"));
		flight.setSeats(80);
		flight.setFare(56000);

		Flight flight2 = new Flight();
		flight2.setId(1);
		flight2.setFlightName("Jet Airways");
		flight2.setSourceCityId(3);
		flight2.setDestinationCityId(1);
		flight2.setDepartureDate(LocalDate.parse("2022-04-13"));
		flight2.setArivalTime(LocalTime.parse("23:00:00"));
		flight2.setSeats(80);
		flight2.setFare(53000);

		flightList.add(flight);
		flightList.add(flight2);
	}

	@Test
	public void setFlightDetails() {

		Mockito.when(flightRepo.save(flight)).thenReturn(flight);

		assertEquals(flightServiceImpl.setFlight(flight), flight);
	}

	@Test
	public void getAllFlights() {

		Mockito.when(flightRepo.findAll()).thenReturn(flightList);

		assertEquals(flightServiceImpl.getAllFlights(), flightList);

	}

	@Test
	public void getFlightById() {

		Mockito.when(flightRepo.findById(flight.getId())).thenReturn(flight);

		assertEquals(flightServiceImpl.getFlightById(1), flight);
	}

	@Test
	public void updateFlight() {

		Mockito.when(flightRepo.save(flight)).thenReturn(flight);

		flight.setSourceCityId(2);
		flight.setDestinationCityId(4);

		assertEquals(flightServiceImpl.updateFlight(flight), flight);
	}

}
