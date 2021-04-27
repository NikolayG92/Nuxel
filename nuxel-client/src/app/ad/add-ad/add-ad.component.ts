import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import {  FormArray, FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import { Router } from '@angular/router';
import { CategoryModel } from 'src/app/category/category-model';
import { CategoryService } from 'src/app/category/category.service';
import { UserService } from 'src/app/user/user.service';
import { AdService } from '../ad.service';

@Component({
  selector: 'app-add-ad',
  templateUrl: './add-ad.component.html',
  styleUrls: ['./add-ad.component.css', '../../../../src/bootstrap.min.css']
})
export class AddAdComponent implements OnInit {

  form: FormGroup;
  @Input() categories: CategoryModel[];
  images = [];
  files = [];

  constructor(private fb: FormBuilder,
              private adService: AdService,
              private router: Router,
              private userService: UserService,
              private categoryService: CategoryService)
         {
        this.form = this.fb.group({
          name: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(30)]],
          description: ['', [Validators.required, Validators.minLength(15), Validators.maxLength(6000)]],
          price: ['', [Validators.required, Validators.min(0)]],
          category: ['', [Validators.required]],
          city: ['', [Validators.required]],
          region: ['', [Validators.required]],
          postCode: ['', [Validators.min(1000), Validators.max(9999)]],
          phoneNumber: ['', [Validators.required, Validators.pattern("^(0[8,9][0-9]{8})$")]],
          images: ['', [Validators.required]]
        });
               }
 
  ngOnInit(): void {
    this.categoryService
      .getAllCategories()
      .subscribe(categories => {
        this.categories = categories;

      });
  }

  submitHandler(): void {
    const data = this.form.value;
    data.category = data.category[0];
    data.userId = this.userService.currentUser.id;
    const formData = new FormData;
    formData.append('ad', new Blob([JSON.stringify(data)], {
      type: "application/json"
    }));
    for (let i = 0; i < this.files.length; i++) {
      formData.append('files', this.files[i]);
      
    }
    this.adService.addAdd(formData).subscribe({
      next: () => {
        this.router.navigate(['/home']);
      }
    });
    
  }

  onFileChange(event) {

    if (event.target.files && event.target.files[0]) {

        var filesAmount = event.target.files.length;
        if(this.images.length + filesAmount > 5){
          alert("You cannot add more than 5 pictures!")
        }else {
        for (let i = 0; i < filesAmount; i++) {
                var reader = new FileReader();
                reader.onload = (event:any) => {
               
                   this.images.push(event.target.result); 
                   this.form.patchValue({
                      fileSource: this.images
                   });

                }
                this.files.push(event.target.files[i]);
                reader.readAsDataURL(event.target.files[i]);
              }
        }

    }

  }

  removeSelectedFile(i){
    this.images.splice(i, 1);
    this.files.splice(i, 1);
  }
}


