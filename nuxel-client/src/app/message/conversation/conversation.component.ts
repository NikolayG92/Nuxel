import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AdService } from 'src/app/ad/ad.service';
import { UserService } from 'src/app/user/user.service';
import { MessageModel } from '../message-model';
import { MessageService } from '../message.service';

@Component({
  selector: 'app-conversation',
  templateUrl: './conversation.component.html',
  styleUrls: ['./conversation.component.css']
})
export class ConversationComponent implements OnInit {

  messages: MessageModel[];
  form: FormGroup;
  conversation: any | null;
  currentUser: any | null;
  otherUserImg;
  ad: any;

  constructor(private messageService: MessageService,
    private route: ActivatedRoute,
    private fb: FormBuilder,
    private userService: UserService,
    private adService: AdService) { 
      this.form = this.fb.group({
      description: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(3000)]]
    })}

  ngOnInit(): void {
    
    this.currentUser = this.userService.currentUser;

    this.route.params.subscribe(params => {
      const conversationId = params['id'];

      this.messageService.getMessagesByCurrentConversation(conversationId, this.currentUser.id)
        .subscribe(data => {
          
          this.adService.getAdById(data.adId)
          .subscribe(data => {
            this.ad = data;
          })

          if(data.buyerId === this.currentUser.id){
            this.userService.getUserById(data.sellerId)
            .subscribe(data => {
              this.otherUserImg = data.profileDetails.imageUrl;
            })
          }else {
            this.userService.getUserById(data.buyerId)
            .subscribe(data => {
              this.otherUserImg = data.profileDetails.imageUrl;
            })
          }
          this.conversation = data;
          this.messages = data.messages;
        })
    })

    window.location.reload;
  }
  
  submitHandler(msgInput: String): void {
    const data = this.form.value;

    data.senderId = this.userService.currentUser.id;

    data.conversationId = this.conversation.id;
    data.description = msgInput;

    this.messageService.sendMessage(data).subscribe({
      next: () => {
         window.location.reload();
      }
    });

  }

}
