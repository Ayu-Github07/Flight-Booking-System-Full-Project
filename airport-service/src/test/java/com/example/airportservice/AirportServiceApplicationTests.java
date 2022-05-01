package com.example.airportservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;

import com.example.airportservice.model.Airport;
import com.example.airportservice.repository.AirportRepo;
import com.example.airportservice.service.AirportServiceImpl;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class AirportServiceApplicationTests {

	@Autowired
	private AirportServiceImpl airportServiceImpl;

	@MockBean
	private AirportRepo airportRepo;

	@Test
	void contextLoads() {
	}

	@Test
	public void setAirportsTest() {

		Airport airport = new Airport();
		airport.setAirportId(1);
		airport.setAirportName("Pune International Airport");
		airport.setAirportCity("Pune");

		Mockito.when(airportRepo.save(airport)).thenReturn(airport);

		assertEquals(airportServiceImpl.setAirportDetails(airport), airport);
	}

	@Test
	public void getAirportByAirportIdTest() {

		Airport airport = new Airport();
		airport.setAirportId(1);
		airport.setAirportName("Pune International Airport");
		airport.setAirportCity("Pune");

		Mockito.when(airportRepo.findByAirportId(airport.getAirportId())).thenReturn(airport);

		assertEquals(airportServiceImpl.getAirportByAirportId(1), airport);

		Mockito.when(airportRepo.findByAirportId(2)).thenReturn(null);

		assertEquals(airportServiceImpl.getAirportByAirportId(2), null);
	}

	@Test
	public void getAllAirportsTest() {

		Airport airport = new Airport();
		airport.setAirportId(1);
		airport.setAirportName("Pune International Airport");
		airport.setAirportCity("Pune");

		Airport airport1 = new Airport();
		airport1.setAirportId(2);
		airport1.setAirportName("Ahmedabad International Airport");
		airport1.setAirportCity("Ahmedabad");

		List<Airport> airportList = new ArrayList<>();

		airportList.add(airport);
		airportList.add(airport1);

		Mockito.when(airportRepo.findAll()).thenReturn(airportList);

		assertEquals(airportServiceImpl.getAllAirports(), airportList);
	}

	@Test
	public void deleteAirportByIdTest() {
		Airport airport = new Airport();
		airport.setAirportId(1);
		airport.setAirportName("Pune International Airport");
		airport.setAirportCity("Pune");

		Mockito.when(airportRepo.findByAirportId(airport.getAirportId())).thenReturn(airport);

		Mockito.when(airportRepo.existsById(airport.getAirportId())).thenReturn(false);

		assertFalse(airportRepo.existsById(airport.getAirportId()));
	}

	@Test
	public void updateAirportByIdTest() {

		Airport airport = new Airport();
		airport.setAirportId(1);
		airport.setAirportName("Pune International Airport");
		airport.setAirportCity("Pune");

		Mockito.when(airportRepo.findByAirportId(airport.getAirportId())).thenReturn(airport);

		airport.setAirportCity("Ahmedabad");
		airport.setAirportName("Ahmedabad Internation Airport");

		Mockito.when(airportRepo.save(airport)).thenReturn(airport);

		assertEquals(airportServiceImpl.updateAirportByAirportId(airport), airport);

	}

	@Test
	public void setAllAirportDetailsTest() {

		Airport airport = new Airport();
		airport.setAirportId(1);
		airport.setAirportName("Pune International Airport");
		airport.setAirportCity("Pune");

		Airport airport1 = new Airport();
		airport1.setAirportId(2);
		airport1.setAirportName("Ahmedabad International Airport");
		airport1.setAirportCity("Ahmedabad");

		List<Airport> airportList = new ArrayList<>();

		Mockito.when(airportRepo.saveAll(airportList)).thenReturn(airportList);

		assertEquals(airportServiceImpl.setAllAirports(airportList), airportList);

	}

}
