import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login/login.service';
import { User } from './userData';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css'],
})
export class UserProfileComponent implements OnInit {
  public username: any;
  public user!: User[];
  project!: User[];
  constructor(private loginService: LoginService) {}

  ngOnInit(): void {
    this.username = this.loginService.getUsername();

    this.loginService.getUserByUsername(this.username).subscribe((data) => {
      this.user = data;
      console.log(`User is:`);

      this.project = Object.values(this.user);
      console.log(this.project);
    });
  }
}
