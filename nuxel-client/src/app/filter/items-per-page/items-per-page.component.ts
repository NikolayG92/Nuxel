import { Component, EventEmitter, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-items-per-page',
  templateUrl: './items-per-page.component.html',
  styleUrls: ['./items-per-page.component.css']
})
export class ItemsPerPageComponent implements OnInit {

  @Output() itemsPerPage: EventEmitter<Number> = new EventEmitter<Number>();

  data:Array<Object> = [
    {id: 0, value: 5},
    {id: 1, value: 10},
    {id: 2, value: 20},
    {id: 3, value: 50}
  ];

  constructor() { }

  ngOnInit(): void {
  }


  showItemsPerPage(items: any){
      this.itemsPerPage.emit(items.target.value)
  }

}
