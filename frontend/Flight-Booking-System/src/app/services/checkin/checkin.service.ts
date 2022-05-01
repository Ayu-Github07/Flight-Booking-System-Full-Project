import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, retry, throwError } from 'rxjs';
import { Seats } from 'src/app/model/seats/seats';

@Injectable({
  providedIn: 'root',
})
export class CheckinService {
  baseUrl = `http://localhost:8007/api/checkin/ticketDetails`;

  public seat!: Seats[];

  constructor(private http: HttpClient) {}

  getSeatsByTicketId(ticketId: string) {
    return this.http.post(`${this.baseUrl}`, ticketId);
  }

  setSeatDetails(seats: any) {
    this.seat = seats;
  }

  getSeatDetails() {
    return this.seat;
  }

  getSeatsForTicketId(ticketId: any): Observable<Seats[]> {
    return this.http.get<Seats[]>(
      `http://localhost:8007/api/checkin/getSeatsByTicketId?ticketId=${ticketId}`
    );
  }

  getSeatsByFlightId(flightId: any, departurDate: any): Observable<Seats[]> {
    return this.http.get<Seats[]>(
      `http://localhost:8007/api/checkin/getTicketsByFlight?flightId=${flightId}&departurDate=${departurDate}`
    );
  }

  getSeatsByTicketIdAndPassengerName(
    ticketId: any,
    passengerName: any
  ): Observable<Seats> {
    return this.http.get<Seats>(
      `http://localhost:8007/api/checkin/getTicketByIdAndPassengerName?ticketId=${ticketId}&passengerName=${passengerName}`
    );
  }
}
