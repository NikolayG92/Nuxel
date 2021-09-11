import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AddAdComponent } from './add-ad/add-ad.component';
import { AdRoutingModule } from './ad-routing.module';
import { AdService } from './ad.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AdsByCategoryComponent } from './ads-by-category/ads-by-category.component';
import { AdDetailsComponent } from './ad-details/ad-details.component';
import { AdsByUserComponent } from './ads-by-user/ads-by-user.component';
import { UserModule } from '../user/user.module';
import { EditAdComponent } from './edit-ad/edit-ad.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FilterModule } from '../filter/filter.module';



@NgModule({
  declarations: [AddAdComponent, AdsByCategoryComponent, AdDetailsComponent, AdsByUserComponent, EditAdComponent],
  imports: [
    CommonModule,
    AdRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    UserModule,
    NgbModule,
    FilterModule,
    
  ],
  providers: [
    AdService
  ]
})
export class AdModule { }
