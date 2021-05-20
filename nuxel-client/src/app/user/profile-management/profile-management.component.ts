import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { UserModel } from '../user-model';
import { UserService } from '../user.service';

@Component({
  selector: 'app-profile-management',
  templateUrl: './profile-management.component.html',
  styleUrls: ['./profile-management.component.css']
})
export class ProfileManagementComponent implements OnInit {

  @Input() user: UserModel;

  image: '';
  selectedImage: File;
  imageUrl: any;
  form: FormBuilder;
  currentUser: any | null;
  id: '';

  constructor(private userService: UserService,
    private router: Router) { }

  ngOnInit(): void {
    this.currentUser = this.userService.currentUser;
    this.image = this.currentUser.profileDetails.imageUrl;
    this.id = this.currentUser.id;
  }

 onImageSelected(event) {
   this.selectedImage = <File>event.target.files[0];
   const formData = new FormData;
   formData.append('file', this.selectedImage, this.selectedImage.name);
   formData.append('user', new Blob([JSON.stringify(this.selectedImage)], {
               type: "application/json"
           }));
   this.userService.changeProfilePicture(formData)
   .subscribe({
    next: () => {
      this.router.navigate(['/user/profile'])
      .then(() => {
        window.location.reload();
      });
    }});

   var reader = new FileReader();
   reader.readAsDataURL(this.selectedImage);
   reader.onload = (_event) => {
     this.imageUrl = reader.result;
     this.image = this.imageUrl;
   }
  }
}
