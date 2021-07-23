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

import { AboutModule } from './about/about.module';
import { CategoryListComponent } from './category/category-list/category-list.component';
import { CategoryComponent } from './category/category/category.component';
import { CategoryModule } from './category/category.module';
import { ProfileManagementComponent } from './user/profile-management/profile-management.component';
import { MessageModule } from './message/message.module';

import { MatProgressBarModule } from '@angular/material/progress-bar';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LoaderService } from './services/loader.service';
import { LoaderInterceptor } from './interceptors/loader-interceptor.service';
import { LoaderComponent } from './shared/loader/loader.component';

export function tokenGetter() {
  return localStorage.getItem('JWT_TOKEN');
}

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    CategoryListComponent,
    CategoryComponent,
    LoaderComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    CoreModule,
    UserModule,
    AdModule,
    AboutModule,
    CategoryModule,
    MessageModule,
    HttpClientModule,
    JwtModule.forRoot({
      config: {
        tokenGetter
      }
    }),
    BrowserAnimationsModule,
    MatProgressBarModule
    ],
  providers: [
    LoaderService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: LoaderInterceptor,
      multi: true
    }

  ],
  bootstrap: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    ProfileManagementComponent
  ]
})
export class AppModule { }
