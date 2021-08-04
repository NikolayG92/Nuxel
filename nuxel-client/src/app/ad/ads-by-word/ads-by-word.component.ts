import { Component, OnInit} from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AdService } from 'src/app/ad/ad.service';
import { AllAdModel } from 'src/app/ad/all-add-model';

@Component({
  selector: 'app-ads-by-word',
  templateUrl: './ads-by-word.component.html',
  styleUrls: ['./ads-by-word.component.css']
})
export class AdsByWordComponent implements OnInit {
  adsByWord: AllAdModel[];
  constructor(private adService: AdService, private route: ActivatedRoute) { 
    
  }
 
  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const word = params['word'];
      this.adService.allByWord(word)
      .subscribe(data => {
        this.adsByWord = data
      });
    })
  }

  onNotifyClicked(nums: number[]){
    const minValue = Number(nums[0]);
    const maxValue = Number(nums[1]);
    
    if(minValue == 0 && maxValue == 0){
      this.adsByWord = this.adService.adsByWord;
    }else if(minValue != 0 && maxValue !=  0){
      this.adsByWord = this.adService.adsByWord.filter((ad) => ad.price >= minValue && ad.price <= maxValue);
    }else if(minValue != 0){
      this.adsByWord = this.adService.adsByWord.filter((ad) => ad.price >= minValue);
    }else if(maxValue != 0){
      this.adsByWord = this.adService.adsByWord.filter((ad) => ad.price <= maxValue);
    }
  }

  onSortingTypeClicked(sortingType: string){
    if(sortingType == "newest"){
      this.adsByWord.sort((a, b) =>  new Date(a.date).getDate() - new Date(b.date).getDate())
    }else if(sortingType == "cheapest"){
      this.adsByWord.sort((a, b) =>  a.price - b.price)
    }else{
      this.adsByWord.sort((a, b) =>  b.price - a.price)
    }
  }
}
