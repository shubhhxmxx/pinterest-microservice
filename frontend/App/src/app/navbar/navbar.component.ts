import { Component } from '@angular/core';
import { AuthService } from '../shared-service/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {
  constructor(public authService:AuthService,private router:Router){}
  ifUserLogged=this.authService.ifUserLogged();
  userName!:string;
  userId:any;

  ngOnInit(){
    this.userName=this.authService.getUserName();
    this.userId=this.authService.getUserId();
    console.log("navbar UserId:"+this.userId);
    
  }

  logout(){
    this.authService.logout();
    this.router.navigate(['/home']);
    
  }

}
