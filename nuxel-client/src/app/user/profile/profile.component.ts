import { Component, Input, OnInit } from '@angular/core';
import { UserModel } from '../user-model';
import { environment } from '../../../environments/environment';
import { UserService } from '../user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  
  currentUser: any | null;

  constructor(private userService: UserService) { 
 
  }

  ngOnInit(): void {
      this.currentUser = this.userService.currentUser;
      let birthDate = new Date(this.currentUser.profileDetails.dateOfBirth);
      let timeDiff = Math.abs(Date.now() - birthDate.getTime());
      
      let age = Math.floor((timeDiff / (1000 * 3600 * 24))/365.25);
      this.currentUser.age = age;
  }

}
