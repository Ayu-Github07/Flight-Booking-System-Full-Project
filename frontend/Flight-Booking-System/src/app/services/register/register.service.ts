import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class RegisterService {
  baseUrl = `http://localhost:8005/registerNewUser`;

  constructor(private http: HttpClient) {}

  setUsers(registration: any) {
    console.log(`Service is also working!`);

    return this.http.post(`${this.baseUrl}`, registration);
  }

  getUserByUsername(username: string): Observable<Object> {
    return this.http
      .get(`http://localhost:8003/getUsers?username=` + username, {
        responseType: 'text',
      })
      .pipe(catchError(this.errorHandler));
  }

  errorHandler(error: HttpErrorResponse) {
    return throwError(() => error);
  }
}
