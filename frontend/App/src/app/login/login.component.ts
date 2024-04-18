import { Component } from '@angular/core';
import { FormGroup,FormBuilder,Validators} from '@angular/forms';
import { UserService } from '../shared-service/user.service';
import { Router } from '@angular/router';
import { AuthService } from '../shared-service/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginForm!:FormGroup;
  errorMessage!:String;
  successMessage!:any;
  constructor(private userService: UserService,private formBuilder:FormBuilder,private router:Router,private authService:AuthService){}
  ngOnInit(){
    this.loginForm=this.formBuilder.group({
      email:['',[Validators.required]],
      password:['',[Validators.required]]
    });
  }
  login(){
    this.successMessage=null;
    this.errorMessage="";
    this.userService.login(this.loginForm.value).subscribe(
      (success)=>{this.successMessage=success;
        console.log(success);
        this.authService.storeUserId(success.userId);
        this.authService.storeUserName(success.userName);
      },
      (err)=>{this.errorMessage=err.error.errorMessage;
        console.log(err);
      },()=>{
        setTimeout(() => {
          this.router.navigate(['home']);
        }, 2000);
      }
    );
    
  }
}
