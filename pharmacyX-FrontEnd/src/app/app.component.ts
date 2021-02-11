import { Component } from '@angular/core';
import { User } from './model/user';
import { LoginService } from './services/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'pharmacyX-FrontEnd';//To-Do Current user logged in!
  public currentUser : User;
  private currentUserEmail: string;

  constructor(
    private router: Router,
    private loginService: LoginService,
) {}

  
  ngOnInit() {
    this.getCurrentUser();
    console.log(this.currentUser);
    if(this.currentUser && (this.currentUser.userRole === "SYSTEM_ADMIN" || this.currentUser.userRole === "PHARMACY_ADMIN")
       && this.currentUser.userStatus === "FIRST_LOGIN") {
      this.router.navigate(['/change-password']);
    }
    

  }

  getCurrentUser(){
    const currentUser = this.loginService.currentUserValue;
    this.currentUser = new User();
    this.currentUser = currentUser;
    this.currentUserEmail = currentUser.email;
  }


  login(){
    window.location.replace('/login')
  }

  register(){
    window.location.replace('/registration')
  }

  logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('currentUser');
    window.location.replace('')
  }
}

