import { Component, DoCheck, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { JwtService } from '../core/auth/services/jwt.service';
import { UserService } from '../user/user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  isLogged = false;

  url: string;
 

  userId: string;
  username: string;
  hasCompletedAccountSetup: boolean;

  constructor(private userService: UserService,
     private jwtHelper: JwtService) { }

  ngOnInit(): void {}

  getUserDetails() {
    this.userId = this.jwtHelper.getUserId;
    this.username = this.jwtHelper.getUsername;
  }

  isLoggedIn() {
    this.getUserDetails();
    return this.userService.isLoggedIn();
  }

}
