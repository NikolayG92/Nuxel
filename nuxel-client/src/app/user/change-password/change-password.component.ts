import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../user.service';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {

  form: FormGroup;
  errorMessage;

  constructor( 
    private fb: FormBuilder,
    private userService: UserService,
    private router: Router) {
      const passwordControl = this.fb.control('', [Validators.required, 
        Validators.pattern("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-_]).{8,}$")]);
      this.form = this.fb.group({
        oldPassword: ['', [Validators.required]],
        newPassword: passwordControl,
        confirmNewPassword: ['', [Validators.required, confirmPasswordValidatorFactory(passwordControl)]]
      });
     }

  ngOnInit(): void {
  }

  
  submitHandler(): void {
    const data = this.form.value;

    this.userService.changePassword(data).subscribe({
      next: () => {
        this.router.navigate(['/user/profile'], {queryParams : { changed: true }});
      },
      error: () => {
        this.errorMessage = 'Your old password is wrong!';
      }
    });
  }

}

export function confirmPasswordValidatorFactory(targetControl: AbstractControl): ValidatorFn {
  return function confirmPasswordValidator(control: AbstractControl): ValidationErrors | null {
    const areTheSame = targetControl.value === control.value;
    return areTheSame ? null : { confirmPasswordValidator: true };
  };
}