import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class ForgotPasswordService {
  username!: string;
  password!: string;
  emailId!: string;

  constructor(private http: HttpClient) {}

  changePassword(username: string, password: string, emailId: string) {
    return this.http.get(
      `http://localhost:8003/forgot-password?username=${username}&password=${password}&emailId=${emailId}`,
      { responseType: 'text' }
    );
  }
}
