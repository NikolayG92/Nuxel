import { Component, EventEmitter, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-sorting-ad',
  templateUrl: './sorting-ad.component.html',
  styleUrls: ['./sorting-ad.component.css']
})
export class SortingAdComponent implements OnInit {

  @Output() sortingType: EventEmitter<string> = new EventEmitter<string>();

  data:Array<Object> = [
    {id: 0, name: "newest"},
    {id: 1, name: "cheapest"},
    {id: 2, name: "expensivest"}
  ];

  constructor() { }

  ngOnInit(): void {
  }


  sortingBy(sortingBy: any){
      this.sortingType.emit(sortingBy.target.value)
  }
}
