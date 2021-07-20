import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FooterComponent } from './footer/footer.component';
import { HeaderComponent } from './header/header.component';
import { RouterModule } from '@angular/router';
import { AuthGuard } from './guards/auth.guard';
import { FormsModule , ReactiveFormsModule } from '@angular/forms';
import { AdsByWordComponent } from '../ad/ads-by-word/ads-by-word.component';
import { FilterModule } from '../filter/filter.module';




@NgModule({
  declarations: [
    FooterComponent, 
    HeaderComponent, 
    AdsByWordComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    ReactiveFormsModule,
    FilterModule
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
