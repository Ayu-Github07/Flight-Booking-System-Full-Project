import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from 'src/app/components/user-dashboard/user-profile/userData';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  baseUrl = `http://localhost:8005/authenticate`;
  userDetails!: any;

  constructor(private http: HttpClient) {}

  //Calling the server to generate Token
  generateToken(credentials: any) {
    return this.http.post(`${this.baseUrl}`, credentials);
  }

  getUserByUsername(username: string): Observable<User[]> {
    return this.http.get<User[]>(
      `http://localhost:8003/user?username=${username}`
    );
  }

  //For Login user
  loginUser(token: string) {
    localStorage.setItem('token', token);
    return true;
  }

  //Check whether user is logged in or not
  isLoggedIn() {
    let token = localStorage.getItem('token');

    if (token == undefined || token === '' || token == null) {
      return false;
    }
    return true;
  }

  //To logout user
  logout() {
    localStorage.clear();
    return true;
  }

  //Get Token
  getToken() {
    return localStorage.getItem('token');
  }

  //TO check whether the role matches or not.
  public roleMatch(allowedRoles: any): boolean {
    let isMatch = false;

    const userRole: any = this.getRole();

    if (userRole != null && userRole) {
      if (userRole === allowedRoles) {
        isMatch = true;
        return isMatch;
      }
    }
    return isMatch;
  }

  setUserDetails(user: any) {
    localStorage.setItem('user', user);
  }

  getUserDetails() {
    return localStorage.getItem('user');
  }

  setUsername(username: any) {
    localStorage.setItem('username', username);
  }

  getUsername() {
    return localStorage.getItem('username');
  }

  setRoles(roles: []) {
    localStorage.setItem('roles', JSON.stringify(roles));
  }

  getRole() {
    return localStorage.getItem('role');
  }

  setRole(role: string) {
    localStorage.setItem('role', role);
  }
}
