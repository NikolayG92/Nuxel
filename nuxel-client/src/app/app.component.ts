import { Component } from '@angular/core';
import { BnNgIdleService } from 'bn-ng-idle';
import { UserService } from 'src/app/user/user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'NuXeL';

  constructor(private bnIdle: BnNgIdleService,
    private userService: UserService,) { 
   
  }

  ngOnInit(): void {
    this.bnIdle.startWatching(86400).subscribe((isTimedOut: boolean) => {
      if (isTimedOut) {
        console.log('session expired');
        this.userService.logout();      
      }
    });
  }

}
