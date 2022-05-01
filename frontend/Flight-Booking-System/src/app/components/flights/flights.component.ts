import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Airport } from 'src/app/model/airport';
import { FlightDetails } from 'src/app/model/flight-details/flight-details';
import { Flight } from 'src/app/model/flight/flight';
import { AirportsService } from 'src/app/services/airports.service';
import { FlightService } from 'src/app/services/flight/flight.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-flights',
  templateUrl: './flights.component.html',
  styleUrls: ['./flights.component.css'],
})
export class FlightsComponent implements OnInit {
  public flights!: Flight[];
  public sourceAirport!: Airport;
  public destinationAirport!: Airport;
  public flightDetails!: FlightDetails[];

  public sourceAirportId!: number;
  public destinationAirportId!: number;

  public sourceAirportCity!: string;
  public destinationAirportCity!: string;

  constructor(
    private flightService: FlightService,
    private airportService: AirportsService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.flightService.getAllFlights().subscribe((data) => {
      console.log('This is working');
      console.log(data);
      this.flights = data;
      console.log('Stored in flight');
      console.log(this.flights);
    });
  }

  updateById(flight: Flight) {
    console.log(flight);
    this.flightService.setFlightDetails(flight);
    this.router.navigate(['../flight-details/add-flight']);
  }

  deleteById(id: any) {
    console.log(`flight id is, ${id}`);

    Swal.fire({
      title: 'Are you sure?',
      text: 'You want to delete this flight details',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, delete it!',
    }).then((result) => {
      if (result.isConfirmed) {
        this.airportService.deleteFlightById(id).subscribe(
          (data) => {
            console.log(data);
            Swal.fire('Deleted!', 'Your file has been deleted.', 'success');
            location.reload();
          },
          (error) => {
            Swal.fire({
              position: 'center',
              icon: 'error',
              title: 'Something went wrong',
              showConfirmButton: false,
              timer: 1500,
            });
            location.reload();
          }
        );
      }
    });
  }
}
