import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SearchBarComponent } from './search-bar/search-bar.component';
import { PriceFilterComponent } from './price-filter/price-filter.component';
import { SortingAdComponent } from './sorting-ad/sorting-ad.component';

@NgModule({
  declarations: [
    SearchBarComponent,
    PriceFilterComponent,
    SortingAdComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    ReactiveFormsModule
  ],
   exports: [
    SearchBarComponent,
    PriceFilterComponent,
    SortingAdComponent
  ]
})
export class FilterModule { }
