import { Time } from '@angular/common';
import { Passenger } from '../passenger/passenger';

export class Ticket {
  bookingId!: number;
  bookingDate!: Date;
  bookingTime!: Time;
  flightId!: number;
  passengers!: Passenger[];
  ticketId!: string;
  totalFare!: number;
  username!: string | null;
}
