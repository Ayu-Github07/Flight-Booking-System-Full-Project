import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { Airport } from '../model/airport';
import { Flight } from '../model/flight/flight';

@Injectable({
  providedIn: 'root',
})
export class AirportsService {
  airportDetails!: Airport;

  baseUrl = `http://localhost:8002/api/airports/`;

  constructor(private http: HttpClient) {}

  getAllAirports(): Observable<Airport[]> {
    return this.http.get<Airport[]>(`${this.baseUrl}`);
  }

  setAirportDetails(data: any): Observable<Object> {
    return this.http.post<Object>(`${this.baseUrl}`, data).pipe(
      tap(
        // Log the result or error
        {
          next: (data) => JSON.stringify(data),

          error: (error) => JSON.stringify(error.text),
        }
      )
    );
  }

  deleteAirportDetails(flightId: any) {
    return this.http.delete(`${this.baseUrl}delete/${flightId}`);
  }

  updateAirportDetails(Airport: Airport) {
    return this.http.put(`${this.baseUrl}update/${Airport.airportId}`, Airport);
  }

  setAirportById(airport: Airport) {
    this.airportDetails = airport;
  }

  getAirportById() {
    return this.airportDetails;
  }

  public setFlightDetails(flight: Flight) {
    console.log('FLight Details Inside service are: ');
    console.log(flight);

    return this.http.post(`http://localhost:8001/api/flights/`, flight, {
      responseType: 'text',
    });
  }

  deleteFlightById(id: any) {
    return this.http.delete(`http://localhost:8001/api/flights/delete/${id}`, {
      responseType: 'text',
    });
  }

  updateFlightDetails(flight: Flight) {
    return this.http.put(
      `http://localhost:8001/api/flights/update/${flight.id}`,
      flight,
      { responseType: 'text' }
    );
  }
}
