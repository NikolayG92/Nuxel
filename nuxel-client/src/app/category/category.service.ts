import { HttpClient } from "@angular/common/http";
import { Injectable} from "@angular/core";
import { environment } from 'src/environments/environment';
import {CategoryModel} from 'src/app/category/category-model'
import { Observable } from 'rxjs';
import { UserService } from "../user/user.service";


@Injectable({
    providedIn: 'root'
  })
  export class CategoryService {
  
    apiUrl = environment.adApiUrl + "/categories";  
    

    constructor(private http: HttpClient) {
      
    }


    getAllCategories(): Observable<CategoryModel[]>{
        return this.http.get<any>(`${this.apiUrl}/all`);
    }

    getById(id : string) : Observable<any> {
      return this.http.get<CategoryModel>(`${this.apiUrl}/${id}`);
    }


  }
  