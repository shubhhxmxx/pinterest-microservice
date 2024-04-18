import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from '../shared-service/user.service';
import { AuthService } from '../shared-service/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-pin',
  templateUrl: './create-pin.component.html',
  styleUrls: ['./create-pin.component.css']
})
export class CreatePinComponent {
  
  uploadPinForm!:FormGroup;
  noOfFollowers!:number;
  userId!:any;
  src!:string;
  createdPin!:boolean;
  file!:File;
  imagePreview!:string;
  boardList!:any[];
  boardName!:any;
  noBoardMessage!:any;
  user!:any;

  constructor(private router :Router ,private formBuilder:FormBuilder,private userService:UserService,private authService :AuthService) {}

  ngOnInit(){
    console.log("UserID for createpin:"+this.authService.getUserId());

    //ideally fetch user details and if success  fetch boards
    this.fetchBoardList();
    this.uploadPinForm=this.formBuilder.group({
      name:["",[]],
      title:["",[Validators.maxLength(100)]],
      description:["",[]],
      url:["",[]],
      src:["",[]],
      userId:[this.authService.getUserId(),[]],
      boardId:["",[]],
      isPrivate:["",[]]
    });

    //fix fetching

    this.userService.fetchUserDetails(this.authService.getUserId()).subscribe(
      (success)=>{
        this.user=success;
        console.log(this.user);
        this.userService.getFollowers(this.user.userId).subscribe(
          (success)=>this.noOfFollowers=success.length
        )
      }
     );
   
  
  }

  fetchBoardList(){
    this.userService.fetchBoards(this.authService.getUserId()).subscribe(
      (success)=>{
        this.boardList=success;
        console.log(this.boardList);
      },(err)=>{
        console.log(err.error.errorMessage);
        this.noBoardMessage=err.error.errorMessage;
      }
    )
  }

  uploadImage(event : any){

   this.file=event.target.files[0];
   const reader=new FileReader();

   reader.onload=()=>{
    this.imagePreview=reader.result as string;
   };
   reader.readAsDataURL(this.file);

  }

  createPin(){
    console.log(this.uploadPinForm.value) ;
     this.userService.createPin(this.uploadPinForm.value).subscribe(
        (success)=>{
          this.setPin(success.pinId);
          this.createdPin=success;
          this.router.navigate(['/home']);
        }
      );
  }
  setPin(pinId:BigInteger){
    const formData=new FormData;
    console.log(pinId);
    formData.append("image",this.file);
    this.userService.storePin(pinId,formData).subscribe(
      (success)=>{console.log("in setPin() from UI");}
    ,(error)=>{console.log(error);}
    );
  }

  createBoard(){
    this.userService.createBoard({userId: this.authService.getUserId(), boardName: this.boardName}).subscribe(
      (success)=>{
        console.log(success);
        console.log(this.boardList);
        this.fetchBoardList();
      },
      (err)=>{
        console.log(err);
      }
    );
      
  }
}
