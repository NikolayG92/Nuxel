import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FooterComponent } from './footer/footer.component';
import { HeaderComponent } from './header/header.component';
import { RouterModule } from '@angular/router';
import { AuthGuard } from './guards/auth.guard';
import { SearchBarComponent } from './search-bar/search-bar.component';
import { FormsModule , ReactiveFormsModule } from '@angular/forms';
import { AdsByWordComponent } from './ads-by-word/ads-by-word.component';





@NgModule({
  declarations: [
    FooterComponent, 
    HeaderComponent, 
    SearchBarComponent, 
    AdsByWordComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [
    AuthGuard
  ],
  exports: [
    HeaderComponent,
    FooterComponent
  ]
})
export class CoreModule { }
