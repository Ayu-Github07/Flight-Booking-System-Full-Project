import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { FlightDetails } from 'src/app/model/flight-details/flight-details';

@Injectable({
  providedIn: 'root',
})
export class FlightDetailsServiceService {
  private baseUrl = 'http://localhost:8003/api/flights/flightDetails';
  flight: any;

  constructor(private http: HttpClient) {}

  getFlightDetails(): Observable<FlightDetails[]> {
    return this.http.get<FlightDetails[]>(`${this.baseUrl}`);
  }

  getFlightDetailsBySourceAndDestinationAndDepartureDate(
    sourceAirportCity: string,
    destinationAirportCity: string,
    departureDate: string
  ): Observable<any> {
    return this.http.get(
      `http://localhost:8003/api/flights/source-to-destination-with-departure?sourceAirportCity=${sourceAirportCity}&destinationAirportCity=${destinationAirportCity}&departureDate=${departureDate}`
    );
  }

  setFlightDetails(flight: any) {
    this.flight = flight;
  }

  getFlights() {
    console.log(this.flight);
    return this.flight;
  }
}
