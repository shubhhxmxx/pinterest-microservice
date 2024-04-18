import { Component } from '@angular/core';
import { UserService } from '../shared-service/user.service';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '../registeration/User';
import { AuthService } from '../shared-service/auth.service';

@Component({
  selector: 'app-userprofile',
  templateUrl: './userprofile.component.html',
  styleUrls: ['./userprofile.component.css']
})
export class UserprofileComponent {

  clickedPinComponent!: any;
  fetchPins: Boolean = false;
  followerCount!:any;
  followingCount!:any;
  errorMessageUser!: any;
  errorMessage!: any;
  successMessage!: any;
  pinFetchingError!: any;
  boardFetchingError!: any;
  pins!: any[];
  user!: any;
  //boardList2:any[]=[{boardName:"Books To Read",pins:2,updatedOn:"2w",src:"../assets/img/books.jpg"},{boardName:"DIY",pins:34,updatedOn:"22w",src:"assets/img/DIY.jpg"}];
  boardList!: any[];
  followerList!: any[];
  followingList!: any[];
  followerIds!: any[];
  followingIds!: any[];
  isFollower!: any;
  isOwner!: any;
  profileOwner!: any;
  userList!:any;
  constructor(private userService: UserService, private activatedRoute: ActivatedRoute, private router: Router, public authService: AuthService) { }


  ngOnInit() {
    this.activatedRoute.params.subscribe(
      (params) => {
        let userId = params['userId'];

        if (this.authService.getUserId() == userId) {
          console.log(this.authService.getUserId());
          console.log(userId);
          this.profileOwner = userId;
          this.isOwner = true;
        }
        else this.isOwner = false;

        console.log("userid from params" + userId)
        this.userService.getFollowers(userId).subscribe(
          (success) => {
            this.followerList = success
            this.followerCount = success.length;
            if(!this.isOwner ){
              this.followerList.forEach(
                (user)=>{
                  console.log(user);
                  
                  if(this.authService.getUserId()==user.userId){
                    this.isFollower=true;
                  }
                }
              )
            }
            console.log(success);
          }, (error) => {
            console.log(error);
          }
        )

        this.userService.getFollowing(userId).subscribe(
          (success) => {
            this.followingCount = success.length;
            this.followingList = success;
            console.log(this.followingList);
          }, (error) => {
            console.log(error);
          }
        )


        this.userService.fetchBoards(userId).subscribe(
          (success) => {
            this.boardList = success;

          }, (err) => {
            this.boardFetchingError = err.error.errorMessage;
            console.log(err.error.errorMessage);
          }
        );
        this.userService.fetchPinsByUserId(userId).subscribe(
          (success) => {
            this.pins = success;
          }
          , (err) => {
            this.pinFetchingError = err.error.errorMessage;
            console.log(this.pinFetchingError);
          }
        )

        //userMSCODE
        this.userService.fetchUserDetails(userId).subscribe(
          (success) => {
            this.user = success;
            console.log(success);
          }, (error) => {
            this.errorMessageUser = error;
            console.log(error);
          }
        )
      }
    )


  }
  clickedBoard(boardId: any) {
    console.log(boardId);
    this.router.navigate(['pins'], { queryParams: { 'boardId': boardId } });
  }

  clickedPins() {
    this.fetchPins = !this.fetchPins;
    console.log(this.pins);
  }
  clickedBoards() {
    this.fetchPins = !this.fetchPins;
  }
  clickedPin(pin: any) {
    this.clickedPinComponent=pin;
  }

  redirectToUser(userId:any){
    this.router.navigate(['user/'+userId]);
  }

  sendFollowRequest() {
    this.activatedRoute.params.subscribe(
      (params) => {
        let profileOwner = params['userId'];
        console.log(profileOwner);
        let userId = (this.authService.getUserId())
        this.userService.followUser(profileOwner, userId).subscribe(
          (success) => {
            console.log("followedUser");
            this.isFollower = true;
            this.userService.getFollowers(profileOwner).subscribe(
              (succ)=>{
                this.followerList=succ;
                this.followerCount=succ.length;
              }
              ,(erro)=>console.log(erro)
            )
          }, (err) => {
            console.log(err.error.errorMessage);
          }
        )
      });
  }
  sendUnFollowRequest() {
    this.activatedRoute.params.subscribe(
      (params) => {
        let profileOwner = params['userId'];
        let userId = (this.authService.getUserId())
        this.userService.unFollowUser(profileOwner,userId).subscribe(
          (success) => {
            console.log("UnfollowedUser");
            this.isFollower = false;
            this.userService.getFollowers(profileOwner).subscribe(
              (succ)=>{
                this.followerList=succ;
                this.followerCount=succ.length;
              }
              ,(erro)=>console.log(erro)
            )
          }, (err) => {
            console.log(err);
          }
        )
        
      });
  }
  deletePin(pinId:any){
    
  }
}