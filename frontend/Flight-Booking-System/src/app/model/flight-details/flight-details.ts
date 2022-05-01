import { Time } from '@angular/common';

export class FlightDetails {
  id!: number;
  flightName!: string;
  sourceAirportCity!: string;
  destinationAirportCity!: string;
  departureDate!: Date;
  arivalTime!: Time;
  seats!: number;
  fare!: number;
}
