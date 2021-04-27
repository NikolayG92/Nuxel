import { Component, Input, OnInit } from '@angular/core';
import {CategoryModel} from 'src/app/category/category-model'

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {

  @Input() category: CategoryModel;

  constructor() { }

  ngOnInit(): void {  }

}
