import { HttpParams } from '@angular/common/http';
import { Component, OnInit, Output } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CategoryModel } from 'src/app/category/category-model';
import { CategoryService } from 'src/app/category/category.service';
import { AllAdModel } from '../all-add-model';
import { AdService } from '../ad.service';
import { UserService } from 'src/app/user/user.service';

@Component({
  selector: 'app-ads-by-category',
  templateUrl: './ads-by-category.component.html',
  styleUrls: ['./ads-by-category.component.css']
})
export class AdsByCategoryComponent implements OnInit {

  adsByCategory: AllAdModel[];
  category: CategoryModel;
  currentUser: any | null;
  isLoading: false;
 
  constructor(private adService: AdService,
    private route: ActivatedRoute,
    private categoryService: CategoryService,
    private userService: UserService) { }

  

  ngOnInit(): void {
    this.currentUser = this.userService.currentUser;
    
    this.route.params.subscribe(params => {

      const id = params['id'];
      this.categoryService.getById(id)
      .subscribe(data => {
        this.category = data
      });
      
      this.adService.adsByCategory(id)
      .subscribe(data => {
       this.adsByCategory = data;
      })
     
    })
    
  }

  onSortingTypeClicked(sortingType: string){
    if(sortingType == "newest"){
      this.adsByCategory.sort((a, b) =>  new Date(a.date).getDate() - new Date(b.date).getDate())
    }else if(sortingType == "cheapest"){
      this.adsByCategory.sort((a, b) =>  a.price - b.price)
    }else{
      this.adsByCategory.sort((a, b) =>  b.price - a.price)
    }
  }
  
  onNotifyClicked(nums: number[]){
    const minValue = Number(nums[0]);
    const maxValue = Number(nums[1]);
    
    if(minValue == 0 && maxValue == 0){
      this.adsByCategory = this.adService.adsByCategoryList;
    }else if(minValue != 0 && maxValue !=  0){
      this.adsByCategory = this.adService.adsByCategoryList.filter((ad) => ad.price >= minValue && ad.price <= maxValue);
    }else if(minValue != 0){
      this.adsByCategory = this.adService.adsByCategoryList.filter((ad) => ad.price >= minValue);
    }else if(maxValue != 0){
      this.adsByCategory = this.adService.adsByCategoryList.filter((ad) => ad.price <= maxValue);
    }
  }
}
