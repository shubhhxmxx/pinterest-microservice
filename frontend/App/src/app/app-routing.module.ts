import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import {  HttpClientModule } from '@angular/common/http';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegisterationComponent } from './registeration/registeration.component';
import { ForgotPasswordComponent } from './login/forgot-password/forgot-password.component';
import { UserprofileComponent } from './userprofile/userprofile.component';
import { CreatePinComponent } from './create-pin/create-pin.component';
import { DisplayPinComponent } from './display-pin/display-pin.component';

export const routes: Routes = [
  {path:'',redirectTo:'home',pathMatch:"full"},
  {path:'home',component:HomeComponent},
  {path:'login',component:LoginComponent},
  {path:'signup',component:RegisterationComponent},
  {path:'forgotPassword',component:ForgotPasswordComponent},
  {path:'user/:userId',component:UserprofileComponent},
  {path:'pins',component:DisplayPinComponent},
  {path:'createPin',component:CreatePinComponent},
  {path:'**', redirectTo:"home",pathMatch:"full"},

];

@NgModule({
  imports: [HttpClientModule,RouterModule.forRoot(routes)],
  exports: [RouterModule],

  bootstrap:[AppComponent]
})
export class AppRoutingModule { }
