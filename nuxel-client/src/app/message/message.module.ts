import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MessageService } from './message.service';
import {MessageRoutingModule} from '../message/message-routing.module';
import {AdRoutingModule} from '../ad/ad-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SendMessageComponent } from './send-message/send-message.component';



@NgModule({
  declarations: [SendMessageComponent],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MessageRoutingModule,
    AdRoutingModule
  ],
  providers: [
    MessageService
  ]
})
export class MessageModule { }
