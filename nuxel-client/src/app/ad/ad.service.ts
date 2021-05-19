import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { environment } from 'src/environments/environment';
import { UserService } from "../user/user.service";
import { AdModel } from "./ad-add-model";
import { AllAdModel } from "./all-add-model";
@Injectable({
  providedIn: 'root'
})
export class AdService {

  apiUrl = environment.adApiUrl + "/ads";


  constructor(private userService: UserService,
    private http: HttpClient) {

  }


  addAdd(ad: FormData) {
    return this.http.post<AdModel>(`${this.apiUrl}/add`, ad);
  }

  adsByCategory(id: string) {
    return this.http.get<AllAdModel[]>(`${this.apiUrl}/allByCategory/${id}`)
  }

  getAdById(id: string) {
    return this.http.get<AllAdModel>(`${this.apiUrl}/details/${id}`);
  }

  getAdsByUserId(id: string) {
    return this.http.get<AllAdModel[]>(`${this.apiUrl}/getAdsByUser/${id}`);
  }

  editAdd(ad: FormData) {
    return this.http.post<AdModel>(`${this.apiUrl}/editAdd`, ad);
  }

}

