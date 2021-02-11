import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { User } from '../model/user';
import { DrugReservationDTO } from '../modelDTO/drugReservationDTO';
import { LoginService } from '../services/login.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {

  public user: User;
  public newPassword: string = "";

  constructor(private userService: UserService, private loginService: LoginService, private router: Router,) {
    this.user = new User();
  }

  ngOnInit() {
    this.userService.getUser().subscribe((data) => {
      this.user = data;
    });
  }

  changePassword() {
    if (this.newPassword === "" ) {
      Swal.fire('Oops...', 'You must fill all fields!', 'error');
    }
    else {
      this.user.password = this.newPassword;
      this.userService.changePassword(this.user);
      alert("Success!");
      this.router.navigate(['']);
      //this.return();      
    }
  }
}
