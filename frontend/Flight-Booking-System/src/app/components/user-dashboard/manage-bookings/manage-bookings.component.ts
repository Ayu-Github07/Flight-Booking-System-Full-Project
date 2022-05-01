import { Component, OnInit } from '@angular/core';
import { CheckIn } from 'src/app/model/check-in/check-in';
import { Seats } from 'src/app/model/seats/seats';
import { Ticket } from 'src/app/model/ticket/ticket';
import { BookingDetailsService } from 'src/app/services/booking-details/booking-details.service';
import { CheckinService } from 'src/app/services/checkin/checkin.service';
import { LoginService } from 'src/app/services/login/login.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-manage-bookings',
  templateUrl: './manage-bookings.component.html',
  styleUrls: ['./manage-bookings.component.css'],
})
export class ManageBookingsComponent implements OnInit {
  checkIn!: CheckIn[];
  seat: any[] = [];
  username!: string | null;
  title: string = 'Booking Successful!';

  constructor(
    private bookingService: BookingDetailsService,
    private checkinService: CheckinService,
    private loginService: LoginService
  ) {}

  ngOnInit(): void {
    this.username = this.loginService.getUsername();

    console.log('Username is: ');
    console.log(`${this.username}`);

    this.bookingService
      .getCheckInByUsername(this.username)
      .subscribe((data) => {
        this.checkIn = data;
        console.log('All The CheckIn Details!');
        console.log(this.checkIn);
        this.checkIn.forEach((x) => {
          if (x.checkedIn) {
            this.checkinService
              .getSeatsByTicketIdAndPassengerName(x.ticketId, x.passengerName)
              .subscribe((data) => {
                console.log(`Passenger seats are: `);

                this.seat.push(data.ticket);
                console.log(this.seat);

                this.seat.forEach((x) => {
                  console.log('Seats in for loop');
                  console.log(x);
                });
              });
          }
        });
      });
  }

  cancelTicket(ticketId: string) {
    Swal.fire({
      title: 'Are you sure?',
      text: "Your Ticket Will Be Cancelled And You Will Refunded Your Money With Term's And Conditions",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, delete it!',
    }).then((result) => {
      if (result.isConfirmed) {
        this.bookingService.deleteBookingByTicketId(ticketId).subscribe(
          (data) => {
            console.log(data);
            Swal.fire('Deleted!', 'Ticket cancelled successfully!.', 'success');
            location.reload();
          },
          (error) => {
            console.log(error);
            Swal.fire('Deleted!', 'Ticket cancelled successfully', 'success');
            location.reload();
          }
        );
      }
    });
  }
}
