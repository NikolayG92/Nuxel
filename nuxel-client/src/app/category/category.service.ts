import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { environment } from 'src/environments/environment';
import { UserService } from "../user/user.service";

@Injectable({
    providedIn: 'root'
  })
  export class CategoryService {
  
    apiUrl = environment.adApiUrl + "/categories";  
    
  
    constructor(private http: HttpClient) {
      
    }

    getAllCategories(){
        return this.http.get<any>(`${this.apiUrl}/all`);
    }


  }
  