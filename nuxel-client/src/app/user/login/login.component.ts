import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  errorMessage = '';

  constructor(private userService: UserService,
    private router: Router) { }

  ngOnInit(): void {
  }

  
  submitFormHandler(formValue: { username: string, password: string }): void {
    this.errorMessage = '';
    this.userService.login(formValue).subscribe({
      next: () => {
        this.router.navigate(['/']);
      },
      error: () => {
        this.errorMessage = "Wrong username or password!";
      }
    });
  }
}
