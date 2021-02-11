import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Address } from '../model/address';
import { User } from '../model/user';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  public user: User;
  public passwordRepeated = '';
  public passwordsEqual = false;

  constructor(
    private userService: UserService,
    private router: Router,
  ) {
    this.user = new User();
    this.user.address = new Address();
  }

  ngOnInit() {
  }

  checkPasswordValidation() {

    if (this.user.password === this.passwordRepeated) {
      this.passwordsEqual = true;
    } else {
      this.passwordsEqual = false;
    }
  }

  addUser() {
    if (this.user.firstName == undefined) {
      alert("You must enter first name.")
    }
    else if (this.user.lastName == undefined) {
      alert("You must enter lastName.")
    }
    else if (this.user.email == undefined) {
      alert("You must enter email.")
    }
    else if (this.user.password == undefined) {
      alert("You must enter password.")
    }
    else if (this.user.address.city == undefined) {
      alert("You must enter city.")
    }
    else if (this.user.address.street == undefined) {
      alert("You must enter city.")
    }
    else if (this.user.address.country == undefined) {
      alert("You must enter city.")
    }
    else if (this.user.address.postalCode == undefined) {
      alert("You must enter city.")
    }
    else if (this.user.phone == undefined) {
      alert("You must enter phone number.")
    }
    else {
      this.userService.addUser(this.user);
      this.router.navigate(['/homepage'])
    }
  }
}