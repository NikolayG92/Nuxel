import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { UserModel } from 'src/app/user/user-model';
import { UserService } from 'src/app/user/user.service';
import { AdService } from 'src/app/ad/ad.service'
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { MessageService } from '../message.service';
import { AllAdModel } from 'src/app/ad/all-add-model'
import { from } from 'rxjs';
import { MessageModule } from '../message.module';
import { MessageModel } from '../message-model';
import { AdModel } from 'src/app/ad/ad-add-model';
import { ConversationModel } from '../conversation-model';
import { ThisReceiver } from '@angular/compiler';

@Component({
  selector: 'app-send-message',
  templateUrl: './send-message.component.html',
  styleUrls: ['./send-message.component.css']
})
export class SendMessageComponent implements OnInit {

  form: FormGroup;
  seller: UserModel;
  buyer: UserModel;
  ad: AllAdModel;
  files = [];
  currentUser: any | null;
  messages: MessageModel[];
  conversation: ConversationModel;

  constructor(private router: Router,
    private route: ActivatedRoute,
    private userService: UserService,
    private adService: AdService,
    private messageService: MessageService,
    private fb: FormBuilder) {
    this.form = this.fb.group({
      description: ['', [Validators.required, Validators.minLength(15), Validators.maxLength(3000)]]
    })
  }

  ngOnInit(): void {

    this.currentUser = this.userService.currentUser;

    this.route.params.subscribe(params => {
        
      const adId = params['id'];


      this.userService.getUserById(this.currentUser.id)
      .subscribe(data => {
        this.buyer = data;
      })

      this.adService.getAdById(adId)
      .subscribe(data => {
        this.ad = data;
        this.userService.getUserById(this.ad.userId)
        .subscribe(data => {
          this.seller = data;
        })
      })

     
    })

  }

  submitHandler(msgInput: String): void {
    const data = this.form.value;

    data.senderId = this.buyer.id;
    data.sellerId = this.seller.id;
    data.adId = this.ad.id;
    data.description = msgInput;

    this.messageService.sendMessage(data).subscribe({
      next: () => {
        this.router.navigate(['ad/details/',this.ad.id], {queryParams : { createMessage: true }});
      }
    });

  }

}
