import { Component, OnInit } from '@angular/core';
import {  FormArray, FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import { UserModel } from 'src/app/user/user-model';
import { UserService } from 'src/app/user/user.service';
import { AdService } from 'src/app/ad/ad.service'
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { MessageService } from '../message.service';
import { AllAdModel } from 'src/app/ad/all-add-model'
import { from } from 'rxjs';

@Component({
  selector: 'app-send-message',
  templateUrl: './send-message.component.html',
  styleUrls: ['./send-message.component.css']
})
export class SendMessageComponent implements OnInit {

  form: FormGroup;
  seller: UserModel;
  buyer: UserModel;
  ad:AllAdModel
  files = [];

  constructor(private router: Router,
    private route: ActivatedRoute,
    private userService: UserService,
    private adService: AdService,
    private messageService: MessageService,
    private fb: FormBuilder) { 
      this.form = this.fb.group({
        description: ['',[Validators.required, Validators.minLength(15), Validators.maxLength(3000)]]
      })
    }

  ngOnInit(): void {

      this.route.params.subscribe(params => {
        
        const sellerId = params['sellerId'];
        const buyerId = params['buyerId'];
        const adId = params['adId'];

        this.userService.getUserById(sellerId)
        .subscribe(data => {
          this.seller = data;
        })

        this.userService.getUserById(buyerId)
        .subscribe(data => {
          this.buyer = data;
        })

        this.adService.getAdById(adId)
        .subscribe(data => {
          this.ad = data;
        })
        
      })
  }

  submitHandler(): void {
    const data = this.form.value;

    data.buyerId = this.userService.currentUser.id;
    data.sellerId = this.seller.id;
    data.adId = this.ad.id;

    this.messageService.sendMassage(data).subscribe({
      next: () => {
        this.router.navigate(['ad/details/',this.ad.id], {queryParams : { createMessage: true }});
      }
    });
    
  }

}
