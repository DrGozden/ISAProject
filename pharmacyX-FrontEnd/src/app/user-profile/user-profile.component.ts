import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { User } from '../model/user';
import { LoginService } from '../services/login.service';
import { UserService } from '../services/user.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  public user: User;
  private userRole: string;
  private fileData: File = null;
  private previewUrl:any = null;
  private fileUploadProgress: string = null;

  constructor(private userService: UserService, private loginService: LoginService, private location: Location, private toastr: ToastrService,
    private route: ActivatedRoute, private http: HttpClient) {
    this.user = new User();
  }

  ngOnInit() {
    if (localStorage.getItem('currentUser') != null) {
      debugger
      let currentUser: any = this.loginService.currentUserValue;
      this.userService.getUser(currentUser.email).subscribe(user => this.user = user);
      currentUser = this.loginService.currentUserValue;
      this.userRole = currentUser.userRole;
    }
  }



  editUser() {
    if (this.user.firstName !== '' && this.user.lastName !== '' && this.user.email !== '' && this.user.phone !== '') {
      this.userService.editUser(this.user);
      this.return();
    }
    else {
      this.toastr.error('All fields must be filled!');
    }
  }

  return() {
    this.location.back()
  }

}
