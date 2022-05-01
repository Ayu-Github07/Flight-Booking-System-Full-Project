import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { Router } from '@angular/router';
import { Airport } from 'src/app/model/airport';
import { AirportsService } from 'src/app/services/airports.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-airport',
  templateUrl: './add-airport.component.html',
  styleUrls: ['./add-airport.component.css'],
})
export class AddAirportComponent implements OnInit {
  airportForm!: FormGroup;
  airportDetails!: Airport;
  updateAirportDetails!: Airport;
  title: string = 'Add Airport Details';
  button: string = 'Add Airport';
  constructor(
    private airportService: AirportsService,
    private formBuilder: FormBuilder,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.airportForm = new FormGroup({
      airportId: new FormControl('', [Validators.required]),
      airportCity: new FormControl('', [
        Validators.required,
        Validators.pattern("^[a-zA-Z -']+"),
      ]),
      airportName: new FormControl('', [
        Validators.required,
        Validators.pattern("^[a-zA-Z -']+"),
      ]),
    });

    this.updateAirportDetails = this.airportService.getAirportById();
    console.log(`Airport Details for updating`);
    console.log(this.updateAirportDetails);
    if (this.updateAirportDetails != null) {
      this.title = 'Update Airport Details';
      this.button = 'Update Airport';
      this.airportForm.controls['airportId'].setValue(
        this.updateAirportDetails.airportId
      );
      this.airportForm.controls['airportName'].setValue(
        this.updateAirportDetails.airportName
      );
      this.airportForm.controls['airportCity'].setValue(
        this.updateAirportDetails.airportCity
      );
    }
  }

  updateAirport(airport: Airport) {}

  onSubmit(airportForm: FormGroup) {
    if (this.updateAirportDetails) {
      this.airportService
        .updateAirportDetails(this.airportForm.value)
        .subscribe(
          (data) => {
            console.log(`Details Added in Database`);

            console.log(data);
            Swal.fire({
              position: 'center',
              icon: 'success',
              title: 'Airport Details Updated Successfully',
              showConfirmButton: false,
              timer: 3500,
            });
            this.airportForm.reset();

            this.router.navigate(['../airport-details']);
            location.reload();
          },
          (error) => {
            Swal.fire({
              position: 'center',
              icon: 'error',
              title: 'Airport Details Not Found!!',
              showConfirmButton: false,
              timer: 2500,
            });
            this.airportForm.reset();
          }
        );
    } else {
      if (airportForm.valid) {
        console.log(`Form is Submitted`);
        console.log(airportForm.value);

        this.airportDetails = airportForm.value;
        console.log(this.airportDetails);

        this.airportService.setAirportDetails(this.airportDetails).subscribe(
          (data) => {
            console.log(`Details Added in Database`);

            console.log(data);
            Swal.fire({
              position: 'center',
              icon: 'success',
              title: 'Airport Details Added Successfully',
              showConfirmButton: false,
              timer: 3500,
            });
            this.airportForm.reset();
            this.router.navigate(['../airport-details']);
          },
          (error) => {
            Swal.fire({
              position: 'center',
              icon: 'error',
              title: 'Airport Details Already Present',
              showConfirmButton: false,
              timer: 2500,
            });
            this.airportForm.reset();
          }
        );
      } else {
        Swal.fire({
          position: 'center',
          icon: 'error',
          title: 'Invalid Form Submission Attempt!',
          text: 'Please Fill All The Details Carefully!',
          showConfirmButton: false,
          timer: 2500,
        });
      }
    }
  }
}
