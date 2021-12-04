import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SearchBarComponent } from './search-bar/search-bar.component';
import { PriceFilterComponent } from './price-filter/price-filter.component';
import { SortingAdComponent } from './sorting-ad/sorting-ad.component';
import { ItemsPerPageComponent } from './items-per-page/items-per-page.component';

@NgModule({
  declarations: [
    SearchBarComponent,
    PriceFilterComponent,
    SortingAdComponent,
    ItemsPerPageComponent
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
    SortingAdComponent,
    ItemsPerPageComponent
  ]
})
export class FilterModule { }
