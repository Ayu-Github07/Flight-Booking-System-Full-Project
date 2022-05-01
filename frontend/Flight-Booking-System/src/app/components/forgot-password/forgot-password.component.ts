import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ForgotPasswordService } from 'src/app/services/forgot-password/forgot-password.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css'],
})
export class ForgotPasswordComponent implements OnInit {
  passwordForm!: FormGroup;
  username!: string;
  password!: string;
  emailId!: string;

  constructor(
    private forgotService: ForgotPasswordService,
    private formBuilder: FormBuilder,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.passwordForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
      emailId: ['', [Validators.required, Validators.email]],
    });
  }

  changePassword(passwordForm: FormGroup) {
    if (this.passwordForm.valid) {
      console.log(this.passwordForm.value);

      this.username = this.passwordForm.value.username;
      this.password = this.passwordForm.value.password;
      this.emailId = this.passwordForm.value.emailId;

      this.validateUsername(this.username, this.password, this.emailId);
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

  validateUsername(username: string, password: string, emailId: string) {
    return this.forgotService
      .changePassword(username, password, emailId)
      .subscribe(
        (data) => {
          console.log(data);

          if (data == 'true') {
            Swal.fire({
              position: 'center',
              icon: 'success',
              title: 'Password Change Successful',
              text: `Login Again!`,
              showConfirmButton: false,
              timer: 2500,
            });

            this.router.navigate(['login']);
          } else if (data == 'false') {
            Swal.fire({
              position: 'center',
              icon: 'error',
              title: 'Invalid Credentials',
              text: 'User data you entered is not correct',
              showConfirmButton: false,
              timer: 1500,
            });
          }
        },
        (error) => {
          Swal.fire({
            position: 'center',
            icon: 'error',
            title: `Doesn't Exists!`,
            text: `User Doesn't Exists!, Create One`,
            showConfirmButton: false,
            timer: 1500,
          });
          this.router.navigate(['register']);
        }
      );
  }
}
