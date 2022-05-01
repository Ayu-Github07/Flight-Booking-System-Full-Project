import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Seats } from 'src/app/model/seats/seats';
import { Ticket } from 'src/app/model/ticket/ticket';
import { CheckinService } from 'src/app/services/checkin/checkin.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-checkin',
  templateUrl: './checkin.component.html',
  styleUrls: ['./checkin.component.css'],
})
export class CheckinComponent implements OnInit {
  public checkinForm!: FormGroup;
  public tickets!: Seats[];
  public ticketId!: string;

  constructor(
    private checkinService: CheckinService,
    private formBuilder: FormBuilder,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.checkinForm = this.formBuilder.group({
      ticketId: ['', Validators.required],
    });
  }

  onSubmit() {
    if (this.checkinForm.valid) {
      console.log('Form submitted!');
      console.log(this.checkinForm.value);

      this.ticketId = this.checkinForm.value;
      this.checkinService.getSeatsByTicketId(this.ticketId).subscribe(
        (data) => {
          console.log('Seats are: ');
          console.log(data);
          Swal.fire({
            position: 'center',
            icon: 'success',
            title: 'Seats Generated Successfully!',
            showConfirmButton: false,
            timer: 2500,
          });
          this.checkinService.setSeatDetails(data);
          this.router.navigate(['check-in/seats']);
        },
        (error) => {
          Swal.fire({
            position: 'center',
            icon: 'error',
            title: 'Invalid Ticket Id',
            showConfirmButton: false,
            timer: 2500,
          });
          this.router.navigate(['']);
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
