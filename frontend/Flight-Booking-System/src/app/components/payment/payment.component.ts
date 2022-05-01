import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BookingDetails } from 'src/app/model/booking-details/booking-details';
import { Passenger } from 'src/app/model/passenger/passenger';
import { BookingDetailsService } from 'src/app/services/booking-details/booking-details.service';
import { LoginService } from 'src/app/services/login/login.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css'],
})
export class PaymentComponent implements OnInit {
  flight: any;
  fare!: number;
  passengers: Passenger[] = [];
  bookingDetails = new BookingDetails();
  booked!: number;
  username!: string | null;
  ticketId!: string;

  options = {
    key: 'rzp_test_7PbkU7vlh4agXK',
    amount: 50000,
    currency: 'INR',
    name: 'Flight Booking System',
    description: 'Payment Integration To Book Your Flight Ticket',
    order_id: '', //This is a sample Order ID. Pass the `id` obtained in the response of Step 1
    handler: function (response: any) {
      Swal.fire({
        position: 'center',
        icon: 'success',
        title: 'Ticket Has Been Booked Successfully!',
        showConfirmButton: false,
        timer: 1500,
      });
      window.location.href = '/success';
    },
    prefill: {
      name: 'Ayush Agrawal',
      email: 'agrawal_ayush@ghrce.raisoni.net',
      contact: '8770835055',
    },
    notes: {
      address: 'Razorpay Corporate Office',
    },
    theme: {
      color: '#3399cc',
    },
  };
  constructor(
    private bookingService: BookingDetailsService,
    private router: Router,
    private loginService: LoginService
  ) {}

  ngOnInit(): void {
    this.flight = this.bookingService.getFlight();
    this.fare = this.bookingService.getFare();
    this.passengers = this.bookingService.getPassengers();
    this.username = this.loginService.getUsername();
    console.log(`Username is: ${this.username}`);
  }
  addBookingDetails() {
    this.bookingDetails.flightId = this.flight['id'];
    this.bookingDetails.username = this.username;
    this.bookingDetails.passengers = this.passengers;
    this.bookingDetails.totalFare = this.fare;

    console.log(`Booking Details of passengers`);
    console.log(this.bookingDetails);

    this.bookingService
      .setBookingDetails(this.bookingDetails)
      .subscribe((data) => {
        this.ticketId = data;
        this.bookingService.setTicketId(this.ticketId);
        console.log(data);
      });
  }

  pay() {
    this.addBookingDetails();

    this.options.amount = this.fare * 100;
    const rzp1 = new this.bookingService.nativeWindow.Razorpay(this.options);

    rzp1.open();

    rzp1.on('payment.failed', function (response: any) {
      alert('Payment Failed Due to unknown reasons');
    });
  }
}
