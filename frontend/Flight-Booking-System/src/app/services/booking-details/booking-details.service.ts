import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CheckIn } from 'src/app/model/check-in/check-in';
import { Ticket } from 'src/app/model/ticket/ticket';

function _window(): any {
  return window;
}

@Injectable({
  providedIn: 'root',
})
export class BookingDetailsService {
  baseUrl = `http://localhost:8006/api/bookings`;

  flight: any;
  fare!: number;
  booking!: any[];
  ticketId!: string;

  constructor(private http: HttpClient) {}

  getAllBookings() {
    return this.http.get(`${this.baseUrl}/`);
  }

  getAllBookingsByUsername(username: any): Observable<Ticket[]> {
    return this.http.get<Ticket[]>(
      `${this.baseUrl}/getByUsername?username=${username}`
    );
  }

  getCheckInDetails(
    flightId: number,
    departurDate: Date
  ): Observable<CheckIn[]> {
    return this.http.get<CheckIn[]>(
      `${this.baseUrl}/getCheckInDetails?flightId=${flightId}&departurDate=${departurDate}`
    );
  }

  setBookingDetails(bookingDetails: any): Observable<any> {
    console.log(bookingDetails);

    return this.http.post(`${this.baseUrl}/`, bookingDetails, {
      responseType: 'text',
    });
  }

  setBooking(flight: any, fare: number, booking: any[]) {
    this.flight = flight;
    this.fare = fare;
    this.booking = booking;
  }

  getFlight() {
    return this.flight;
  }

  getCheckInByUsername(username: any): Observable<CheckIn[]> {
    return this.http.get<CheckIn[]>(
      `http://localhost:8006/api/bookings/getCheckInDetails/username?username=${username}`
    );
  }

  getBookingDetailsByTicketId(ticket: any): Observable<Ticket> {
    return this.http.get<Ticket>(
      `http://localhost:8006/api/bookings/getByTicketId/${ticket}`
    );
  }

  deleteBookingByTicketId(ticketId: any) {
    return this.http.delete(
      `http://localhost:8006/api/bookings/bookingDetails?ticketId=${ticketId}`
    );
  }

  getFare() {
    return this.fare;
  }

  getPassengers() {
    return this.booking;
  }

  setTicketId(ticketId: string) {
    localStorage.setItem('ticket', ticketId);
  }

  getTicketId() {
    return localStorage.getItem('ticket');
  }

  get nativeWindow(): any {
    return _window();
  }
}
