import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { UserModel } from 'src/app/user/user-model';
import { UserService } from 'src/app/user/user.service';
import { environment } from 'src/environments/environment';
import { AdModel } from '../ad-add-model';
import { AdService } from '../ad.service';
import { AllAdModel } from '../all-add-model';

@Component({
  selector: 'app-ad-details',
  templateUrl: './ad-details.component.html',
  styleUrls: ['./ad-details.component.css']
})
export class AdDetailsComponent implements OnInit {

  ad: AllAdModel;
  currentImage;
  currentIndex = 0;
  seller: UserModel;

  constructor(private adService: AdService,
    private router: Router,
    private route: ActivatedRoute,
    private userService: UserService) { }

  ngOnInit(): void {

    this.route.params.subscribe(params => {

      const id = params['id'];
  
      this.adService.getAdById(id)
      .subscribe(data => {
        this.ad = data;
        this.currentImage = data.images[this.currentIndex];
        
        this.userService.getSellerById(data.userId)
        .subscribe(data => {
          this.seller = data;
        })
      });

    }
    );

  }
    

  isLoggedIn() {
    return this.userService.isLoggedIn();
  }

  changePhoto(direction: string){
    if(direction == '-'){
      this.currentIndex--;
    }else {
      this.currentIndex++;
      
    }
    this.currentImage = this.ad.images[this.currentIndex];
  }

}
