import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AddAdComponent } from './add-ad/add-ad.component';
import { AdRoutingModule } from './ad-routing.module';
import { AdService } from './ad.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';



@NgModule({
  declarations: [AddAdComponent],
  imports: [
    CommonModule,
    AdRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [
    AdService
  ]
})
export class AdModule { }
