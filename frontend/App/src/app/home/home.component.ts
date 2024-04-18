import { Component } from '@angular/core';
import { UserService } from '../shared-service/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  pins!:any[];
  errorMessage!:any;
  clickedPinComponent!:any;
  userDetailsForPin!:any;

  constructor(private userService:UserService,private router:Router){}

  ngOnInit(){
    this.userService.fetchHomeFeed().subscribe(
      (success)=>{
        this.pins=success;
        
      },(err)=>{
        this.errorMessage=err;
        console.log(this.errorMessage);
      }
    );
  }

  clickedPin(pin:any){
    console.log(pin.userId);
    this.userService.fetchUserDetails(pin.userId).subscribe((success)=>{
      this.userDetailsForPin=success;
      console.log("pin Owned by:"+success.userName);
      },(err)=>{
        console.log(err);
      }
    
    )
    this.clickedPinComponent=pin;
  }
  redirectToUser(userId:any){
    this.router.navigate(['user/'+userId]);
  }


}
