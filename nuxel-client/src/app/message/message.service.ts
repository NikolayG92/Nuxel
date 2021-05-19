import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { environment } from 'src/environments/environment';
import { SendMessage } from './send-message-model';
 
@Injectable({
  providedIn: 'root'
})
export class MessageService {

  apiUrl = environment.adApiUrl + "/messages";  

  constructor(  private http: HttpClient) { 

  }

  sendMassage(message: FormData){
    return this.http.post<SendMessage>(`${this.apiUrl}/send`, message);
  }

}
