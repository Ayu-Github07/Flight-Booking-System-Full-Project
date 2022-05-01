import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login/login.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})
export class NavbarComponent implements OnInit {
  public loggedIn = false;
  public role!: string | null;
  public admin = false;
  public username!: string | null;
  constructor(private loginService: LoginService) {}

  ngOnInit(): void {
    this.loggedIn = this.loginService.isLoggedIn();
    this.username = this.loginService.getUsername();
    this.role = this.loginService.getRole();
    if (this.role == 'ADMIN') {
      this.admin = true;
      console.log(`this.admin`, this.admin);
    }
    console.log(this.role);
  }

  logoutUser() {
    this.loginService.logout();
    window.location.href = '/';
  }
}
