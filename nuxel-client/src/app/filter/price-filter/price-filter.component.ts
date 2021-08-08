import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { AdService } from 'src/app/ad/ad.service';
import { AllAdModel } from 'src/app/ad/all-add-model';

@Component({
  selector: 'app-price-filter',
  templateUrl: './price-filter.component.html',
  styleUrls: ['./price-filter.component.css']
})
export class PriceFilterComponent implements OnInit {
  adsByWord: AllAdModel[]; 
  @Output() notify: EventEmitter<any> = new EventEmitter<any>();

  constructor(private adService: AdService) { }

  ngOnInit(): void {
  }

  keyPress(event: any) {
    const pattern = /[0-9]/;
    let inputChar = String.fromCharCode(event.charCode);
    if (event.keyCode != 8 && !pattern.test(inputChar)) {
      event.preventDefault();
    }
  }

  onSubmit(minValue: Number,maxValue: Number){
    var nums = []
    nums.push(minValue,maxValue)
    this.notify.emit(nums);
  }
  
}
