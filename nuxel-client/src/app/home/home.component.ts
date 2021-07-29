import { Component, DoCheck, OnChanges, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { JwtService } from '../core/auth/services/jwt.service';
import { UserService } from '../user/user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent{

  isLogged = false;

  url: string;
  userId: string;
  username: string;
  hasCompletedAccountSetup: boolean;
  infoMessage = '';

  constructor(private userService: UserService,
     private jwtHelper: JwtService,
     private route: ActivatedRoute) { }

  ngOnInit(): void {

    this.route.queryParams
    .subscribe(params => {
      if(params.createdAdd !== undefined && params.createdAdd === 'true') {
          this.infoMessage = 'You created add successfully!';
      }else if(params.editAdd !== undefined && params.editAdd === 'true'){
          this.infoMessage = 'You edit add successfully!';
      }
    });
  }

  getUserDetails() {
    this.userId = this.jwtHelper.getUserId;
    this.username = this.jwtHelper.getUsername;
  }

  isLoggedIn() {
    this.getUserDetails();
    return this.userService.isLoggedIn();
  }

}
