import { Component } from '@angular/core';
import { UserService } from '../shared-service/user.service';
import { ActivatedRoute } from '@angular/router';
import { AuthService } from '../shared-service/auth.service';

@Component({
  selector: 'app-display-pin',
  templateUrl: './display-pin.component.html',
  styleUrls: ['./display-pin.component.css']
})
export class DisplayPinComponent {

  pins!:any;
  userId!:any;
  boardId!:number;
  errorMessage!:any;
  clickedPinComponent!:any;
  collaborationbutton!:any;
  enableEditButton!:any;
  collaborateButton!:any;
  

  constructor(private userService:UserService,private router:ActivatedRoute,private auth:AuthService){}

  ngOnInit(){
    

    let userId=(this.router.snapshot.queryParamMap.get("userId"))||null;
    let boardId=(this.router.snapshot.queryParamMap.get("boardId"))||null;
    
    if(boardId==null){
          this.userService.fetchPinsByUserId(this.userId).subscribe((success)=>{
            this.pins=success;
            console.log(this.pins);
            
          },(err)=>{
            this.errorMessage=err;
            console.log(this.errorMessage);
          }
          )
        }

    else if(userId==null){
          this.userService.fetchPinsByBoardId(boardId).subscribe((success)=>{
            this.pins=success;
            if(this.pins!=null){
              this.collaborateButton=this.pins[0].userId==this.auth.getUserId();
              console.log(this.pins[0].userId);
              console.log(this.pins)
            }
          },(err)=>{
            this.errorMessage=err;
            console.log(this.errorMessage);
          }
      )
    }
    else{
      this.userService.fetchHomeFeed().subscribe(
        (success)=>{
        this.pins=success;
        console.log(this.pins);
      },(err)=>{
        this.errorMessage=err;
        console.log(err);
      }
      );
    }
  }

  clickedPin(pin:any){
    this.clickedPinComponent=pin;
    if(this.auth.getUserId()==pin.userId){
      console.log("euendudjbdjn");
    }
    else {
      console.log("ndinifirirfioifnrinonvfnokonkfnofonifdvniofriknofnok");
    }
  }
}
