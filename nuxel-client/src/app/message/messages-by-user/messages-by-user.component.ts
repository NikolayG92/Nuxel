import { Component, Input, OnInit } from '@angular/core';
import { UserService } from 'src/app/user/user.service';
import { MessageService } from '../message.service';
import { MessageModel } from '../message-model';
import { AdService } from 'src/app/ad/ad.service';
import { ConversationModel } from '../conversation-model';

@Component({
  selector: 'app-messages-by-user',
  templateUrl: './messages-by-user.component.html',
  styleUrls: ['./messages-by-user.component.css']
})
export class MessagesByUserComponent implements OnInit {

  @Input() conversationsByUser: ConversationModel[];
  currentUser: any | null;
  sender: any | null;

  constructor(private messageService: MessageService,
    private userService: UserService,
    private adService: AdService) {

  }

  ngOnInit(): void {

    this.currentUser = this.userService.currentUser;
    const id = this.currentUser.id;
    this.messageService.getConversationsByUserId(id)
      .subscribe(data => {
        data.forEach(conversation => {
          let ad = this.adService.getAdById(conversation.adId);
          ad.subscribe(ad => {
            conversation.adImage = ad.images[0];
            conversation.adName = ad.name;
           
              this.userService.getUserById(conversation.buyerId)
              .subscribe(user => {
                if(user.id !== this.currentUser.id){
                  this.userService.getUserById(conversation.buyerId)
                  .subscribe(us => {
                    conversation.senderName = us.username;
                  })
                  
                }else {
                  this.userService.getUserById(conversation.sellerId)
                  .subscribe(us => {
                    conversation.senderName = us.username;
                  })
                }
              })
              ;
            
          });
        })
       this.conversationsByUser = data;
      });
  }

}
