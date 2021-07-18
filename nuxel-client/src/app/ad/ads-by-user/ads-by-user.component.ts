import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/user/user.service';
import { AdService } from '../ad.service';
import { AllAdModel } from '../all-add-model';

@Component({
  selector: 'app-ads-by-user',
  templateUrl: './ads-by-user.component.html',
  styleUrls: ['./ads-by-user.component.css']
})
export class AdsByUserComponent implements OnInit {
   
  adsByUser: AllAdModel[];
  currentUser: any | null;

  constructor(private adService: AdService,
    private router: Router,
    private userService: UserService) { }

  ngOnInit(): void {
   
      this.currentUser = this.userService.currentUser;
      const id = this.currentUser.id;
      this.adService.getAdsByUserId(id)
      .subscribe(data => {
        this.adsByUser = data
      }
      );
     
  }

  removeAd(id: string) {
    this.adService.deleteAd(id).subscribe(
      {
        next: () => {this.router.navigate(['/'])}
      }
    );
  }

}
