import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Airport } from 'src/app/model/airport';
import { Flight } from 'src/app/model/flight/flight';

@Injectable({
  providedIn: 'root',
})
export class FlightService {
  flight!: Flight;

  baseUrl = `http://localhost:8003/api/flights/`;

  airportUrl = `http://localhost:8003/api/airports/`;
  constructor(private http: HttpClient) {}

  getAllFlights(): Observable<Flight[]> {
    return this.http.get<Flight[]>(`${this.baseUrl}`);
  }

  getAirportById(airportId: any): Observable<Airport> {
    return this.http.get<Airport>(`${this.airportUrl}${airportId}`);
  }

  setFlightDetails(flight: any) {
    this.flight = flight;
  }

  getFlightDetails() {
    return this.flight;
  }
}
