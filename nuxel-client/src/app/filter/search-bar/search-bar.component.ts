import { Component, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import {AdService} from 'src/app/ad/ad.service';


@Component({
  selector: 'app-search-bar',
  templateUrl: './search-bar.component.html',
  styleUrls: ['./search-bar.component.css']
})
export class SearchBarComponent implements OnInit {

  constructor(private adService: AdService , private router: Router) { 
   
  }

  ngOnInit(): void {
   
  }

  onSubmit(value: string){
    this.adService.allByWord(value)
    .subscribe( () => {
       this.router.navigate([`ad/ads-by-word/${value}`]);
    });
  }
}
