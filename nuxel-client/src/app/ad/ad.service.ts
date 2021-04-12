import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { environment } from 'src/environments/environment';
import { UserService } from "../user/user.service";
import { AdModel } from "./ad-add-model";

@Injectable({
    providedIn: 'root'
  })
  export class AdService {
  
    apiUrl = environment.adApiUrl + "/ads";  
    
  
    constructor(private userService: UserService,
      private http: HttpClient) {
      
    }


    addAdd(ad: FormData){
      return this.http.post<AdModel>(`${this.apiUrl}/add`, ad);
    }

  }
  
  