import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, of, throwError } from 'rxjs';
import { catchError, delay, map, tap } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Tokens } from './tokens';
import { UserModel } from './user-model';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  apiUrl = environment.usersApiUrl + "/users";
  currentUser: any | null;
  resp: HttpResponse<String>;
  loggedUser: string;

  private JWT_TOKEN = 'JWT_TOKEN';


  isLoggedIn$: BehaviorSubject<boolean>;

  constructor(private http: HttpClient) {
    this.isLoggedIn$ = new BehaviorSubject<boolean>(this.isLoggedIn());
  }



  login(user: { username: string, password: string }): Observable<boolean> {
    return this.http.post<any>(`${this.apiUrl}/login`, user)
      .pipe(
        tap(token => this.doLoginUser(user.username, token))
      );
  }

  register(user: { username: string, email: string, password: string }) {
    return this.http.post(`${this.apiUrl}/register`, user)
    .pipe(
      catchError(error => {
        return Error;
      })
    
      )
  }

  changeProfilePicture(user: FormData): Observable<any> {
    return this.http.post(`${this.apiUrl}/changeProfilePicture`, user);
  }

  changePassword(user: FormData): Observable<any>{
    return this.http.post(`${this.apiUrl}/changePassword`, user);
  }

  changeProfileDetails(user: FormData): Observable<any> {
    return this.http.post(`${this.apiUrl}/changeProfileDetails`, user);
  }
  
  logout() {
    this.isLoggedIn$.next(false);
    this.loggedUser = null;
    this.removeTokens();
  }

  getCurrentUserProfile(): Observable<any> {

    return this.http.get(`${this.apiUrl}/profile`).pipe(
      tap(((user: UserModel) => this.currentUser = user)),
      catchError(error => {
        this.currentUser = null;
        return error;
      })
    );
  }



  isLoggedIn() {
    return !!this.getJwtToken();
  }

  getJwtToken() {
    return localStorage.getItem(this.JWT_TOKEN);
  }

  private doLoginUser(username: string, tokens: Tokens) {
    this.isLoggedIn$.next(true);
    this.loggedUser = username;
    this.storeTokens(tokens);
    this.getCurrentUserProfile();
  }


  private storeTokens(tokens: Tokens) {
    localStorage.setItem(this.JWT_TOKEN, tokens.jwt);
  }

  private removeTokens() {
    localStorage.removeItem(this.JWT_TOKEN);
  }
}

