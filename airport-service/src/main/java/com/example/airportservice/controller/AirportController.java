package com.example.airportservice.controller;

import java.util.List;
import java.util.Optional;

import com.example.airportservice.model.Airport;
import com.example.airportservice.service.AirportServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/airports")
@CrossOrigin(origins = "http://localhost:4200")
public class AirportController {

    final static Logger logger = LoggerFactory.getLogger(AirportController.class);

    @Autowired
    private AirportServiceImpl airportServiceImpl;

    // Created By: Ayush Agrawal
    // On Date: 05/04/2022
    // Description: Controller method designed for retrieving all the airports from
    // the service layer.
    @GetMapping("/")
    public ResponseEntity<List<Airport>> getAllAirports() {
        logger.info("Method Get All Airports Is Working!!");
        try {

            logger.info("Get All Airport Details From The Method Inside try block is working:");

            return ResponseEntity.ok().body(airportServiceImpl.getAllAirports());
        } catch (Exception e) {
            logger.warn("An Exception occured inside getAllAirports method in Airport Service");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Created By: Ayush Agrawal
    // On Date: 05/04/2022
    // Description: This method is defined to retrieve all the airport details by
    // using airport Id from the service layer of airport microservice.
    // Exception Case: When the id is null or the data coming from service layer is
    // a null object.
    @GetMapping("/{airportId}")
    public ResponseEntity<Airport> getAirportByAirportId(@PathVariable("airportId") long airportId) {
        logger.info("Method getAirportByAirportId is working properly");
        try {
            logger.info("Inside try block of method getAirportByAirportId is working properly");
            return ResponseEntity.ok().body(airportServiceImpl.getAirportByAirportId(airportId));
        } catch (Exception e) {
            logger.warn(
                    "An Exception occured inside getAirportByAirportId() method of airport controller microservice");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Created By: Ayush Agrawal
    // On Date: 05-04-2022
    // Description: This controller method is defined to retrieve all the airports
    // by Airport Name No matter from which city the airport is located from the
    // service layer.
    // Exception case: When the airport name is null or when no such airport name
    // found.
    @GetMapping("/Airport-Name")
    public ResponseEntity<List<Airport>> getAirportsByAirportName(@RequestParam("airportName") String airportName) {

        logger.info("Method getAirportsByAirportName is working properly");

        try {
            logger.info("Inside try block Method getAirportsByAirportName is working properly");
            return ResponseEntity.ok().body(airportServiceImpl.getAirportByAirportName(airportName));
        } catch (Exception e) {
            logger.warn(
                    "An Exception occured inside getAirportsByAirportName() method of airport controller microservice");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Created By: Ayush Agrawal
    // On Date: 05-04-2022
    // Description: This method will set the details of airport retreiving a JSON
    // body from the http server request via client.
    // Exception case: When Id is null,name and city name is null,object is null and
    // duplicate id is present.
    @PostMapping("/")
    public ResponseEntity<Airport> setAirports(@RequestBody Airport airport) {
        logger.info("This method setAirports() inside airport controller is working properly");
        try {

            logger.info("Inside Try of method setAirports() inside airport controller is working properly");

            List<Airport> airports = airportServiceImpl.getAllAirports();

            for (Airport a : airports) {
                if (a.getAirportId() == airport.getAirportId() || (a.getAirportName().equals(airport.getAirportName())
                        && a.getAirportCity().equals(airport.getAirportCity()))) {
                    throw new Exception();
                }
                System.out.println("Airport Details Stored are: " + a);
                System.out.println("Airport Details Provided are: " + airport);
            }

            airportServiceImpl.setAirportDetails(airport);
            return ResponseEntity.of(Optional.of(airport));
        } catch (Exception e) {
            logger.warn(
                    "An Exception occured inside getAirportsByAirportName() method of airport controller microservice");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Created By: Ayush Agrawal
    // On Date: 05-04-2022
    // Description: This method is implemented to set all the airports send via
    // request through http post request.
    @PostMapping("/All-Airports")
    public ResponseEntity<String> setAllAirports(@RequestBody List<Airport> airports) {

        logger.info("This method inside ");

        try {

            airportServiceImpl.setAllAirports(airports);
            return ResponseEntity.ok().body("All Airport Details Are Added Successfully!");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Created By: Ayush Agrawal
    // On Date: 06-04-2022
    // Description: This method is designed to update the airport details using id.
    @PutMapping("/update/{airportId}")
    public ResponseEntity<Airport> updateAirportById(@PathVariable("airportId") long airportId,
            @RequestBody Airport airport) {
        try {
            List<Airport> airports = airportServiceImpl.getAllAirports();
            boolean flag = false;
            for (Airport a : airports) {
                if (a.getAirportId() == airportId) {
                    flag = true;
                    airportServiceImpl.updateAirportByAirportId(airport);
                }
            }

            if (flag == false) {
                throw new Exception();
            }
            return ResponseEntity.ok().body(airport);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Created By: Ayush Agrawal
    // On Date: 06/04/2022
    // Description: This method is designed to delete the airport by using airport
    // id.
    @DeleteMapping("/delete/{airportId}")
    public ResponseEntity<String> deleteAirportById(@PathVariable("airportId") long airportId) {
        try {
            List<Airport> airports = airportServiceImpl.getAllAirports();
            boolean flag = false;
            for (Airport a : airports) {
                if (a.getAirportId() == airportId) {
                    flag = true;
                    airportServiceImpl.deleteAirportByAirportId(airportId);
                }
            }

            if (flag == false) {
                throw new Exception();
            }
            return ResponseEntity.ok().body("Airport Details Deleted Successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Airport Details Not Found!!");
        }
    }
}
