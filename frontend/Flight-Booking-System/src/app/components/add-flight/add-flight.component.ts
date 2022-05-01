import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Flight } from 'src/app/model/flight/flight';
import { AirportsService } from 'src/app/services/airports.service';
import { FlightService } from 'src/app/services/flight/flight.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-flight',
  templateUrl: './add-flight.component.html',
  styleUrls: ['./add-flight.component.css'],
})
export class AddFlightComponent implements OnInit {
  public flightForm!: FormGroup;
  public flight!: Flight;
  public updateFlight!: Flight;

  title: string = 'Add Flight Details';
  button: string = 'Register New Flight';
  constructor(
    private formBuilder: FormBuilder,
    private flightService: AirportsService,
    private airportService: FlightService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.flightForm = this.formBuilder.group({
      id: ['', Validators.required],
      flightName: ['', Validators.required],
      sourceCityId: ['', Validators.required],
      destinationCityId: ['', Validators.required],
      departureDate: ['', Validators.required],
      arivalTime: ['', Validators.required],
      seats: ['', Validators.required],
      fare: ['', Validators.required],
    });

    this.updateFlight = this.airportService.getFlightDetails();
    console.log('Flight Details are: ');
    console.log(this.updateFlight);

    if (this.updateFlight != null) {
      this.title = 'Update Flight Details';
      this.button = 'Update Flight';

      console.log('FLight is : ', this.updateFlight.id);

      this.flightForm.patchValue({
        id: this.updateFlight.id,
        flightName: this.updateFlight.flightName,
        sourceCityId: this.updateFlight.sourceCityId,
        destinationCityId: this.updateFlight.destinationCityId,
        departureDate: this.updateFlight.departureDate,
        arivalTime: this.updateFlight.arivalTime,
        seats: this.updateFlight.seats,
        fare: this.updateFlight.fare,
      });
    }
  }

  onSubmit() {
    if (this.updateFlight != null) {
      this.flightService.updateFlightDetails(this.flightForm.value).subscribe(
        (data) => {
          console.log(data);
          Swal.fire({
            position: 'center',
            icon: 'success',
            title: 'Flight Details Updated Successfully',
            showConfirmButton: false,
            timer: 3500,
          });
          location.reload();
          this.flightForm.reset();
          this.router.navigate(['../flight-details']);
        },
        (error) => {
          console.log(error);
          Swal.fire({
            position: 'center',
            icon: 'error',
            title: 'Flight Details Cannot Be Updated',
            text: `Something Went Wrong`,
            showConfirmButton: false,
            timer: 2500,
          });
          location.reload();
          this.flightForm.reset();
          this.router.navigate(['../flight-details']);
        }
      );
    } else {
      console.log('Form is Submitted');
      console.log(this.flightForm.value);

      this.flight = this.flightForm.value;
      console.log(`Flight Details are: `);
      console.log(this.flight);

      this.flightService.setFlightDetails(this.flight).subscribe(
        (data) => {
          console.log(data);

          Swal.fire({
            position: 'center',
            icon: 'success',
            title: 'Flight Details Added Successfully',
            showConfirmButton: false,
            timer: 3500,
          });
          this.flightForm.reset();
          this.router.navigate(['../flight-details']);
        },
        (error) => {
          console.log(error);

          Swal.fire({
            position: 'center',
            icon: 'error',
            title: 'Flight Details Cannot Be Added',
            text: `Something Went Wrong`,
            showConfirmButton: false,
            timer: 2500,
          });
          this.flightForm.reset();
        }
      );
    }
  }
}
