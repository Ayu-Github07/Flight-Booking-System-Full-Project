import { Time } from '@angular/common';

export class Flight {
  id!: number;
  flightName!: string;
  sourceCityId!: number;
  destinationCityId!: number;
  departureDate!: Date;
  arivalTime!: Time;
  seats!: number;
  fare!: number;
}
