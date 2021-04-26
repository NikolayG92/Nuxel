import { Component, OnInit , Input } from '@angular/core';
import { from } from 'rxjs';
import { CategoryModel } from 'src/app/category/category-model';
import {CategoryService} from 'src/app/category/category.service';

@Component({
  selector: 'app-category-list',
  templateUrl: './category-list.component.html',
  styleUrls: ['./category-list.component.css']
})
export class CategoryListComponent implements OnInit {
  @Input() categories: CategoryModel[];

  constructor(private categoryService: CategoryService) {
    
  }

  ngOnInit(): void {
    this.categoryService
    .getAllCategories()
    .subscribe(categories => {
        this.categories = categories;
       
    });

  }
}
