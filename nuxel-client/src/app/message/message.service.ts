import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { environment } from 'src/environments/environment';
import { MessageModel } from './message-model';
import { Observable } from 'rxjs';
import { UserService } from '../user/user.service';
import { ConversationModel } from './conversation-model';
 
@Injectable({
  providedIn: 'root'
})
export class MessageService {

  apiUrl = environment.adApiUrl + "/messages";  
  currentUser: any | null;
  
  constructor(  private http: HttpClient,
    private userService: UserService) {
      this.currentUser = this.userService.currentUser; 
     
  }

  sendMessage(message: FormData){
    return this.http.post<MessageModel>(`${this.apiUrl}/send`, message);
  }

  getConversationsByUserId(id: String) : Observable<ConversationModel[]>{
    return this.http.get<any>(`${this.apiUrl}/byUser/${id}`);
  }
  
  getMessagesByCurrentConversation(id: String, senderId: String) : Observable<ConversationModel>{
    return this.http.get<any>(`${this.apiUrl}/byConversation/${id}/${senderId}`);
  }

  createNewConversation(id: String, senderId: String) : Observable<ConversationModel>{
    return this.http.get<any>(`${this.apiUrl}/createNewConversation/${id}/${senderId}`);
  }

  
}
