import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CategoryComponent } from './category/category.component';
import { CategoryListComponent } from './category-list/category-list.component';
import { RouterModule } from '@angular/router';
import { CategoryService } from './category.service';
import { CategoryRoutingModule } from './category-routing.module';
import { AdsByCategoryComponent } from '../ad/ads-by-category/ads-by-category.component';
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
