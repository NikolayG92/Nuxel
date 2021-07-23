import { Component, Input, OnInit } from '@angular/core';
import { LoaderService } from 'src/app/services/loader.service';

@Component({
  selector: 'app-loader',
  templateUrl: './loader.component.html',
  styleUrls: ['./loader.component.css']
})
export class LoaderComponent implements OnInit {

  @Input() isLoading: boolean;

  constructor(private loaderService: LoaderService) {
    
    this.loaderService.isLoading.subscribe((v) => {
      this.isLoading = v;
    });
   }

  ngOnInit(): void {
  }

}