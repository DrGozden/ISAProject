import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { User } from '../model/user';
import { LoginService } from '../services/login.service';
import { UserService } from '../services/user.service';
import { Location } from '@angular/common';
import Swal from 'sweetalert2/dist/sweetalert2.js';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  public user: User;
  private userRole: string;

  constructor(private userService: UserService, private loginService: LoginService, private location: Location, private toastr: ToastrService,
    private route: ActivatedRoute, private http: HttpClient) {
    this.user = new User();
  }

  ngOnInit() {
    if (localStorage.getItem('currentUser') != null) {
      this.user = this.loginService.currentUserValue;
    }
    else{
      this.return();
    }
  }



  editUser() {
    debugger
    if (this.user.firstName !== '' && this.user.lastName !== '' && this.user.email !== '' && this.user.phone !== '' && this.user.address.city !==''
                      && this.user.address.street !=='' && this.user.address.country !=='' && this.user.address.postalCode !=='' ) {
      this.userService.editUser(this.user);
      this.return();
    }
    else {
      Swal.fire('Oops...', 'You must fill all fields!', 'error');
    }
  }

  return() {
    this.location.back()
  }

}
