import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../user.service';

@Component({
  selector: 'app-profile-details',
  templateUrl: './profile-details.component.html',
  styleUrls: ['./profile-details.component.css']
})
export class ProfileDetailsComponent implements OnInit {

  form: FormGroup;
  errorMessage;
  dateOfBirth;
  currentUser: any | null;

  constructor( 
    private fb: FormBuilder,
    private userService: UserService,
    private router: Router) {
      this.form = this.fb.group({
        firstName: [''],
        lastName: [''],
        dateOfBirth: [''],
        phoneNumber: ['', [Validators.pattern("^(0[8,9][0-9]{8})$")]],
        gender: ['']
      });
     }

  ngOnInit(): void {
    this.currentUser = this.userService.currentUser;
  }

  submitHandler(): void {
    const data = this.form.value;
    
    
    let currentDate = new Date();
    let dateOfBirth = new Date(data.dateOfBirth);
    if(dateOfBirth > currentDate){
     this.errorMessage = "Please enter valid date!";
    }else {
     this.userService.changeProfileDetails(data).subscribe({
      next: () => {
        this.router.navigate(['/user/profile']);
      }
    });
    }
  }
}
  
