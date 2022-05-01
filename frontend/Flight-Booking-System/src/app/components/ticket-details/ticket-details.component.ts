import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CheckIn } from 'src/app/model/check-in/check-in';
import { Seats } from 'src/app/model/seats/seats';
import { BookingDetailsService } from 'src/app/services/booking-details/booking-details.service';
import { CheckinService } from 'src/app/services/checkin/checkin.service';

@Component({
  selector: 'app-ticket-details',
  templateUrl: './ticket-details.component.html',
  styleUrls: ['./ticket-details.component.css'],
})
export class TicketDetailsComponent implements OnInit {
  public seats!: Seats[];
  public searchBooking!: FormGroup;

  constructor(
    private checkService: CheckinService,
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

    this.checkService
      .getSeatsByFlightId(
        this.searchBooking.value.flightId,
        this.searchBooking.value.departurDate
      )
      .subscribe((data) => {
        this.seats = data;
      });
  }
}
