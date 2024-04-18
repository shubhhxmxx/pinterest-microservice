import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor() { }
  userName!:any;
  storeUserName(Name:any){
    console.log(Name);
    localStorage.setItem('userName',Name);
  }

  storeUserId(userId:BigInt){
    console.log(userId);

    localStorage.setItem('userId',String(userId));
  }
  ifUserLogged():boolean{
    return localStorage.getItem('userId')!==null;
  }

  getUserId():any{
    const userNumberAsString=localStorage.getItem('userId');
    if(userNumberAsString!=null){
      const number=parseInt(userNumberAsString);
      return number;
    }return -1;
  }
  getUserName():any{
    return this.userName;
  }

  logout():void{
    console.log("logout Called");
    localStorage.removeItem('userId');
    localStorage.removeItem('userName');
    console.log(localStorage.getItem('userId'));
  }
}
