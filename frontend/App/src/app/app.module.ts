import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {FormsModule,ReactiveFormsModule} from '@angular/forms'
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegisterationComponent } from './registeration/registeration.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { NavbarComponent } from './navbar/navbar.component';
import { HttpClientModule } from '@angular/common/http';
import { UserService } from './shared-service/user.service';
import { ForgotPasswordComponent } from './login/forgot-password/forgot-password.component';
import { UserprofileComponent } from './userprofile/userprofile.component';
import { CreatePinComponent } from './create-pin/create-pin.component';
import { DatePipe } from './date.pipe';
import { DisplayPinComponent } from './display-pin/display-pin.component';
import { PinDatePipe } from './display-pin/pin-date.pipe';


@NgModule({
  declarations: [
    AppComponent,
    RegisterationComponent,
    LoginComponent,
    HomeComponent,
    NavbarComponent,
    ForgotPasswordComponent,
    UserprofileComponent,
    CreatePinComponent,
    DatePipe,
    DisplayPinComponent,
    PinDatePipe
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [UserService],
  bootstrap: [AppComponent]
})
export class AppModule { }
