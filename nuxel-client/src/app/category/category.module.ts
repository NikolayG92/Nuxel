import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { CategoryRoutingModule } from './category-routing.module';
import { AdModule } from '../ad/ad.module';



@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule,
    CategoryRoutingModule,
    AdModule
  ]
})
export class CategoryModule { }
