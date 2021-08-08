import { Component, DoCheck, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { Router } from '@angular/router';
import { MessageService } from 'src/app/message/message.service';
import { UserService } from 'src/app/user/user.service';
import { JwtService } from '../auth/services/jwt.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent{

  userId: string;
  username: string;
  hasCompletedAccountSetup: boolean;
  unreadMessages = false;
  currentUser: any | null;

  constructor(private userService: UserService,
    private router: Router,
    private jwtHelper: JwtService,
    private messageService: MessageService) {

    }

  ngOnInit(): void {
    
    this.userService.getCurrentUserProfile()
    .subscribe(data => {
      this.currentUser = data;
      this.hasUnreadMessages();
    });
  
  }
    
  getUserDetails() {
    this.userId = this.jwtHelper.getUserId;
    this.username = this.jwtHelper.getUsername;

  }

  hasUnreadMessages() {
    this.messageService.getConversationsByUserId(this.currentUser.id)
    .subscribe(conversations => {
      conversations.forEach(conversation => {
        conversation.messages.forEach(message => {
          if(message.unread && message.senderId != this.currentUser.id){
            this.unreadMessages = true;
            return;
          }
        })
      })
    })
    this.unreadMessages = false;

  }


  isLoggedIn() {

    this.getUserDetails();
    return this.userService.isLoggedIn();
  }

  logout() {
    this.userService.logout();
    this.router.navigate(['/'])
      .then(() => {
        window.location.reload();
      });
  }

}
