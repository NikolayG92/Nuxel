import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MessageService } from './message.service';
import {MessageRoutingModule} from '../message/message-routing.module';
import {AdRoutingModule} from '../ad/ad-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SendMessageComponent } from './send-message/send-message.component';
import { MessagesByUserComponent } from './messages-by-user/messages-by-user.component';
import { UserService } from '../user/user.service';
import { UserModule } from '../user/user.module';
import { ConversationComponent } from './conversation/conversation.component';



@NgModule({
  declarations: [SendMessageComponent, MessagesByUserComponent, ConversationComponent],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MessageRoutingModule,
    AdRoutingModule,
    UserModule
  ],
  providers: [
    MessageService
  ]
})
export class MessageModule { }
