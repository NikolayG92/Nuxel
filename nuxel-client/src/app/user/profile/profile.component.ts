import { Component, Input, OnInit } from '@angular/core';
import { UserModel } from '../user-model';
import { environment } from '../../../environments/environment';
import { UserService } from '../user.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  
  currentUser: any | null;
  registeredOn: string;
  infoMessage = '';

  constructor(private userService: UserService,
    private route: ActivatedRoute) { 
 
  }

  ngOnInit(): void {


    this.route.queryParams
      .subscribe(params => {
        if(params.changed !== undefined && params.changed === 'true') {
            this.infoMessage = 'You have changed your profile successfully!';
        }
      });

      this.currentUser = this.userService.currentUser;
      this.registeredOn = this.currentUser.registeredOn;
      this.registeredOn = this.registeredOn.replace('T', ' ');
      this.currentUser.registeredOn = this.registeredOn;
      let birthDate = new Date(this.currentUser.profileDetails.dateOfBirth);
      let timeDiff = Math.abs(Date.now() - birthDate.getTime());
      
      let age = Math.floor((timeDiff / (1000 * 3600 * 24))/365.25);
      this.currentUser.age = age;
  }

}
