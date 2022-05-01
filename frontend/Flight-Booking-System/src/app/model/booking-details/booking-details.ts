import { Passenger } from '../passenger/passenger';

export class BookingDetails {
  flightId!: number;
  username!: string | null;
  passengers!: Passenger[];
  totalFare!: number;
}
