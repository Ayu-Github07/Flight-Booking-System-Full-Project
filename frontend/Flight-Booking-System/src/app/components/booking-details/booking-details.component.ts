import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Passenger } from 'src/app/model/passenger/passenger';
import { BookingDetailsService } from 'src/app/services/booking-details/booking-details.service';
import { FlightDetailsServiceService } from 'src/app/services/flight-details/flight-details-service.service';

@Component({
  selector: 'app-booking-details',
  templateUrl: './booking-details.component.html',
  styleUrls: ['./booking-details.component.css'],
})
export class BookingDetailsComponent implements OnInit {
  flight!: any;
  fare!: number;
  totalFare!: number;
  passenger = new Passenger();
  dataArray: Passenger[] = [];

  index!: number;
  date!: Date;
  passengerForm!: FormGroup;

  constructor(
    private flightService: FlightDetailsServiceService,
    private bookingService: BookingDetailsService,
    private formBuilder: FormBuilder,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.flight = this.flightService.getFlights();
    this.fare = this.flight.fare;
    this.passenger = new Passenger();
    this.dataArray.push(this.passenger);
  }

  addForm() {
    this.passenger = new Passenger();
    this.dataArray.push(this.passenger);
  }

  removeForm(index: number) {
    this.dataArray.splice(index, 1);
  }

  // this.router.navigate(['/payment']);

  onSubmit() {
    console.log(this.dataArray);
    this.totalFare = this.fare * this.dataArray.length;
    this.bookingService.setBooking(this.flight, this.totalFare, this.dataArray);
    this.router.navigate(['../payment']);
  }
}
