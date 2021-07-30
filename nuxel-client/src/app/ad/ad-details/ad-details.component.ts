import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { MessageModule } from 'src/app/message/message.module';
import { MessageService } from 'src/app/message/message.service';
import { UserModel } from 'src/app/user/user-model';
import { UserService } from 'src/app/user/user.service';
import { AdService } from '../ad.service';
import { AllAdModel } from '../all-add-model';
import { RatingModel } from '../ratingModel';

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
  buyer: UserModel;
  infoMessage = '';  
  currentUser: any | null;
  conversation: any | null;
  ratingModel: RatingModel;
  isRated;
  reviews: any;

  constructor(private adService: AdService,
    private route: ActivatedRoute,
    private userService: UserService,
    private messageService: MessageService) { }

  ngOnInit(): void {
    

    this.currentUser = this.userService.currentUser;

    this.route.params.subscribe(params => {

      const id = params['id'];
  
      this.adService.getAdById(id)
      .subscribe(data => {
        this.ad = data;
        this.currentImage = data.images[this.currentIndex];
        this.userService.getUserById(data.userId)
        .subscribe(data => {
          this.seller = data;
          this.reviews = this.seller.profileDetails.reviews;
          
          this.reviews.forEach(review => {
             if(review.buyerId == this.buyer.id){
               this.isRated = true;
             }
          });
        })
      });
      
    })
    this.buyer = this.currentUser;
    this.route.queryParams
    .subscribe(params => {
      if(params.createMessage !== undefined && params.createMessage === 'true') {
          this.infoMessage = 'You sent message successfully!';
      }
    });
    this.hasConversation();
  }
  
  userRate(userRating: Number,sellerId: String,buyerId: String){
    this.ratingModel = new RatingModel();
    this.ratingModel.rating = userRating;
    this.ratingModel.sellerId = sellerId;
    this.ratingModel.buyerId = buyerId;
    const formData = this.ratingModel;
  
    this.userService.rateSeller(formData)
    .subscribe(() => {
      window.location.reload();
    });
  }

  isLoggedIn() {
    return this.userService.isLoggedIn();
  }

  hasConversation(){
    
    this.messageService.getConversationsByUserId(this.currentUser.id)
    .subscribe(data => {
      data.forEach(conv => {
        if(conv.adId === this.ad.id){
     
        if(conv.buyerId === this.currentUser.id){
          this.conversation = conv;
        }
      }
      })
           
    });
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
