import { Component, DoCheck, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/user/user.service';
import { JwtService } from '../auth/services/jwt.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {

  userId: string;
  username: string;
  hasCompletedAccountSetup: boolean;

  constructor(private userService: UserService,
     private router: Router, 
     private jwtHelper: JwtService) { 
    
     }

  ngOnInit(): void {
   
  }

  getUserDetails() {
    this.userId = this.jwtHelper.getUserId;
    this.username = this.jwtHelper.getUsername;  
    
 }
    

  isLoggedIn() {

    this.getUserDetails();
    return this.userService.isLoggedIn();
  }

  logout() {
    this.userService.logout();
    this.router.navigate(['/'])
    .then(() => {
      window.location.reload();
    });
  }

}
