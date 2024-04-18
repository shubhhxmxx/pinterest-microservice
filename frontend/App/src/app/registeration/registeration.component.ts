import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { UserService } from '../shared-service/user.service';
import { Router } from '@angular/router';
import { AuthService } from '../shared-service/auth.service';
@Component({
  selector: 'app-registeration',
  templateUrl: './registeration.component.html',
  styleUrls: ['./registeration.component.css']
})
export class RegisterationComponent {
  registerationForm!: FormGroup;
  file!: any;
  imagePreview!: string;


  errorMessage!: any;
  successMessage: any;


  constructor(private userService: UserService, private formBuilder: FormBuilder, private router: Router, private authService: AuthService) { }
  ngOnInit() {
    this.registerationForm = this.formBuilder.group({
      userName: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.pattern('^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$')]],
      confirmPassword: ['', [Validators.required]],
      mobileNumber: ['', [Validators.required, Validators.pattern("[0-9]{10}")]],
    });
  }

  register() {
    console.log(this.registerationForm.value);
    this.errorMessage = "";
    this.successMessage = null;

    this.userService.register(this.registerationForm.value).subscribe(
      (success) => {
        console.log(success);
        this.successMessage = success;
        this.authService.storeUserId(success.userId);
        this.authService.storeUserName(success.userName);
        this.setProfilePic(this.authService.getUserId());
        
      }

      , (er) => {
        console.log(er.error.errorMessage);
        this.errorMessage = er.error.errorMessage;
      },
      () => {
        setTimeout(() => {
          this.router.navigate(['/home'])
        }, 2000);
      }
    );

  }



  uploadImage(event: any) {

    this.file = event.target.files[0];
    const reader = new FileReader();

    reader.onload = () => {
      this.imagePreview = reader.result as string;
    };
    reader.readAsDataURL(this.file);

  }


  setProfilePic(userId: BigInteger) {
    const formData = new FormData;
    console.log(userId);
    formData.append("image", this.file);
    this.userService.uploadProfilePic(userId, formData).subscribe(
      (success) => { console.log("in setPin() from UI"); }
      , (error) => { console.log(error); }
    );
  }

}
