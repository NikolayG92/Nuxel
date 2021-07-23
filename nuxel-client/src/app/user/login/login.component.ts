import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  errorMessage = '';
  infoMessage = '';

  constructor(private userService: UserService,
    private router: Router,
    private route: ActivatedRoute) { }

  ngOnInit() {
        this.route.queryParams
          .subscribe(params => {
            if(params.registered !== undefined && params.registered === 'true') {
                this.infoMessage = 'Registration Successful! Please Login!';
            }
          });
    }

  
  submitFormHandler(formValue: { username: string, password: string }): void {
    this.errorMessage = '';
    this.userService.login(formValue).subscribe({
      next: () => {
        this.router.navigate(['/'])
        .then(() => {
          window.location.reload();
        });
      },
      error: () => {
        this.errorMessage = "Wrong username or password!";
      }
    });
  }
}
