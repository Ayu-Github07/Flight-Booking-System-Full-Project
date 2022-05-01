import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Ticket } from 'src/app/model/ticket/ticket';

@Injectable({
  providedIn: 'root',
})
export class SuccessService {
  baseUrl = `http://localhost:8006/api/bookings/getByTicketId`;

  constructor(private http: HttpClient) {}

  getBookingDetailsByTicketId(ticketId: any): Observable<Ticket> {
    console.log('Service is working');

    return this.http.get<Ticket>(`${this.baseUrl}/${ticketId}`);
  }
}
