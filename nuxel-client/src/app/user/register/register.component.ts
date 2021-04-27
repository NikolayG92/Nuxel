import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  errorMessage = '';
  form: FormGroup;

  constructor( 
    private fb: FormBuilder,
    private userService: UserService,
    private router: Router) {
      const passwordControl = this.fb.control('', [Validators.required, 
        Validators.pattern("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-_]).{8,}$")]);
      this.form = this.fb.group({
        username: ['', [Validators.required, Validators.minLength(4)]],
        password: passwordControl,
        confirmPassword: ['', [Validators.required, confirmPasswordValidatorFactory(passwordControl)]],
        email: ['', [Validators.required, Validators.email]]
      });
     }

  ngOnInit(): void {
  }

  submitHandler(): void {
    const data = this.form.value;

    this.userService.register(data).subscribe({
      next: () => {
        this.router.navigate(['/user/login'], {queryParams: { registered: true }});
      },
      error: (error) => {
           this.errorMessage = "User with that credentials already exists!";
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