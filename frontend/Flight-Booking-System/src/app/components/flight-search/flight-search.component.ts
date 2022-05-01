import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { FlightDetails } from 'src/app/model/flight-details/flight-details';
import { FlightDetailsServiceService } from 'src/app/services/flight-details/flight-details-service.service';
import { LoginService } from 'src/app/services/login/login.service';

@Component({
  selector: 'app-flight-search',
  templateUrl: './flight-search.component.html',
  styleUrls: ['./flight-search.component.css'],
})
export class FlightSearchComponent implements OnInit {
  public flightDetails: FlightDetails[] = [];
  public flightForm!: FormGroup;
  flight!: any;
  public loggedIn = false;

  constructor(
    private router: Router,
    private loginService: LoginService,
    private formBuilder: FormBuilder,
    private flightService: FlightDetailsServiceService
  ) {}

  ngOnInit(): void {
    this.loggedIn = this.loginService.isLoggedIn();
    this.flightForm = this.formBuilder.group({
      source: ['', Validators.required],
      destination: ['', Validators.required],
      departureDate: ['', Validators.required],
    });
    this.flightService.getFlightDetails().subscribe((data: FlightDetails[]) => {
      this.flightDetails = data;
    });
  }

  searchFlight(flightForm: any) {
    console.log(flightForm);
    console.log(flightForm.value);
    return flightForm;
  }

  getFlight() {
    if (this.flightForm.valid) {
      this.flightService
        .getFlightDetailsBySourceAndDestinationAndDepartureDate(
          this.flightForm.value.source,
          this.flightForm.value.destination,
          this.flightForm.value.departureDate
        )
        .subscribe((data: FlightDetails[]) => {
          this.flightDetails = data;
          console.log(this.flightDetails);
          console.log(this.flightForm.value.departureDate.type);
        });
    } else {
      this.flightService
        .getFlightDetails()
        .subscribe((data: FlightDetails[]) => {
          this.flightDetails = data;
        });
    }
  }

  get source() {
    return this.flightForm.get('source');
  }

  get destination() {
    return this.flightForm.get('destination');
  }

  get departureDate() {
    return this.flightForm.get('departureDate');
  }

  setFlightDetails(flight: any) {
    this.flightService.setFlightDetails(flight);
  }

  navigate() {
    this.router.navigateByUrl('/login');
  }
}
