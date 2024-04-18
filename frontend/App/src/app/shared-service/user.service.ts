import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { User } from '../registeration/User';
import { Observable } from 'rxjs'
import { Board } from '../userprofile/Board';
@Injectable()
export class UserService {
  
  
  
  constructor(private http: HttpClient) { }

  fetchUserDetails(userData:any):Observable<User>{
    return this.http.get<User>(
      "http://localhost:8765/user/"+userData
    )
  }

  register(userData:any):Observable<User>{
    return this.http.post<User>(
      "http://localhost:8765/register",
      userData
    );
  }
  login(userData:any):Observable<any>{
    return this.http.post<User>(
      "http://localhost:8765/login",
      userData
    )

  }
  resetPassword(userData:any):Observable<any>{
    return this.http.post<User>(
      "http://localhost:8765/forgotPassword",
      userData
    );
  }

  fetchBoards(userData:any):Observable<any []>{
    console.log(userData);
    let url="http://localhost:8766/getBoards/"+userData;
    return this.http.get<Board[]>(
      url
    );
  }

  fetchPinsByUserId(userData:any):Observable<any[]>{
    let url="http://localhost:8766/getPins/user/"+userData;
    return this.http.get<any[]>(
      url
    );
  }
  fetchPinsByBoardId(userData:any):Observable<any[]>{
    let url="http://localhost:8766/getPins/board/"+userData;
    return this.http.get<any[]>(
      url
    );
  }

  fetchHomeFeed():Observable<any[]>{
    let url="http://localhost:8766/homeFeed"
    return this.http.get<any[]>(
      url
    );
  }
  createPin(userData:any) : Observable<any>{
    
    let url="http://localhost:8766/createPin" ;
    return <Observable<string>> this.http.post(url, userData);
  }

  uploadProfilePic(userId: Uint8Array, formData: FormData) {
    let url="http://localhost:8765/storeProfilePic/"+userId;
    return <Observable<any>> this.http.post(url,formData);
    
  }

  storePin(pinId : BigInteger,userData:any):Observable<any>{
    let url="http://localhost:8766/storePin/"+pinId;
    return <Observable<any>> this.http.post(url,userData);
  }

  createBoard(userData:any):Observable<any>{
    let url="http://localhost:8766/createBoard";
    return this.http.post<any>(url,userData);
  }


  getFollowers(userId:any):Observable<any>{
    let url="http://localhost:8768/getFollowers/"+userId;
    return this.http.get<any>(url);
  }

  getFollowing(userId:any):Observable<any>{
    let url="http://localhost:8768/getFollowing/"+userId;
    return this.http.get<any>(url);
  }

  followUser(userId:any,followerId:any):Observable<any>{
    let url="http://localhost:8768/addFollower/"+userId+"/"+followerId;
    return this.http.get<any>(url);

  }
  unFollowUser(userId:any,followerId:any):Observable<any>{
    let url="http://localhost:8768/removeFollower/"+userId+"/"+followerId;
    return this.http.delete<any>(url);
    
  }
}
