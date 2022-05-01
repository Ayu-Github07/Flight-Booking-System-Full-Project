import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { CheckIn } from 'src/app/model/check-in/check-in';
import { BookingDetailsService } from 'src/app/services/booking-details/booking-details.service';

@Component({
  selector: 'app-booking',
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.css'],
})
export class BookingComponent implements OnInit {
  public checkIn!: CheckIn[];
  public searchBooking!: FormGroup;

  constructor(
    private bookingService: BookingDetailsService,
    private formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
    this.searchBooking = this.formBuilder.group({
      flightId: ['', Validators.required],
      departureDate: ['', Validators.required],
    });
  }
  getBooking() {
    console.log(`Form is Submitted!`);
    console.log(this.searchBooking.value);

    this.bookingService
      .getCheckInDetails(
        this.searchBooking.value.flightId,
        this.searchBooking.value.departureDate
      )
      .subscribe((data) => {
        this.checkIn = data;
        console.log(`Check In Details`);
        console.log(this.checkIn);
      });
  }
}
