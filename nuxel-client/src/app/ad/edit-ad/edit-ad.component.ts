import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from 'src/app/user/user.service';
import { AdService } from '../ad.service';
import { AllAdModel } from '../all-add-model';

@Component({
  selector: 'app-edit-ad',
  templateUrl: './edit-ad.component.html',
  styleUrls: ['./edit-ad.component.css']
})
export class EditAdComponent implements OnInit {

  form: FormGroup;
  images = [];
  files = [];
  image: AllAdModel;
  ad: AllAdModel;

  constructor(private userService: UserService,
    private adService: AdService,
    private router: Router,
    private route: ActivatedRoute,
    private fb: FormBuilder) { 
      this.form = this.fb.group({      
        description: ['', [Validators.required, Validators.minLength(15), Validators.maxLength(6000)]],
        price: ['', [Validators.required, Validators.min(0)]],
        images: ['', [Validators.required]]
      });
    }

  ngOnInit(): void {

    this.route.params.subscribe(params => {
      const id = params['id'];
      this.adService.getAdById(id)
      .subscribe(data => {
        this.ad = data;
        let images = [];
        images = data.images;
        images.forEach(img => {
          this.images.push(img.url);
        })
      
      });
    })
   
  }


  submitHandler(): void {
    const data = this.form.value;
    data.userId = this.userService.currentUser.id;
    data.adId = this.ad.id;
    const formData = new FormData;
    formData.append('ad', new Blob([JSON.stringify(data)], {
      type: "application/json"
    }));
    for (let i = 0; i < this.files.length; i++) {
      formData.append('files', this.files[i]);
      
    }
    this.adService.editAdd(formData).subscribe({
      next: () => {
        this.router.navigate(['/home'], {queryParams : { editAdd: true }});
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
