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

  constructor(
    private router: Router,
    private loginService: LoginService,
) {}

  
  ngOnInit() {
    this.getCurrentUser();
  }

  getCurrentUser(){
    const currentUser = this.loginService.currentUserValue;
    this.currentUser = new User();
    this.currentUser = currentUser;
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

