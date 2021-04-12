import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { HeaderComponent } from './core/header/header.component';
import { FooterComponent } from './core/footer/footer.component';
import { CoreModule } from './core/core.module';
import { UserModule } from './user/user.module';
import { TokenInterceptor } from './core/auth/token.interceptor';
import { JwtModule } from '@auth0/angular-jwt';
import { AdModule } from './ad/ad.module';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';


export function tokenGetter() {
  return localStorage.getItem('JWT_TOKEN');
}

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    CoreModule,
    UserModule,
    AdModule,
    HttpClientModule,
    JwtModule.forRoot({
      config: {
        tokenGetter
      }
    })
    ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    }

  ],
  bootstrap: [
    AppComponent,
    HeaderComponent,
    FooterComponent
  ]
})
export class AppModule { }
