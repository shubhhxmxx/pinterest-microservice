import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators,FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from 'src/app/shared-service/user.service';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent {
  
  constructor(private formBuilder:FormBuilder,private userService:UserService,private router:Router){}

  successMessage:any;
  errorMessage:any;
  forgotPasswordForm!:FormGroup;
  ngOnInit(){
      this.forgotPasswordForm=this.formBuilder.group({
        email:['',[Validators.email]],
        mobileNumber:['',[Validators.required,Validators.pattern("[0-9]{10}")]],
        password:['',[Validators.required,Validators.pattern('^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$')]],
        confirmPassword:['',[Validators.required]]
      });
    }
    
    validate(){
      this.userService.resetPassword(this.forgotPasswordForm.value).subscribe(
        (success)=>{
          this.successMessage="Password Changed Successfully";
          console.log(this.successMessage);
          ()=>{
            setTimeout(() => {
              this.router.navigate(['/home']);
            }, 1000);
          }
        },(err)=>{
          console.log(err);
          this.errorMessage=err.error.errorMessage;
        }
      );
    }
}
