import { Component, OnInit } from '@angular/core';
import { LoginDTO } from '../modelDTO/loginDTO';
import { LoginService } from '../services/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public loginDTO: LoginDTO;
  constructor(private loginService: LoginService, private router: Router) { }

  ngOnInit() {
    const currentUser = this.loginService.currentUserValue;
    if(currentUser){
      this.router.navigate(['']);
    }
    else{
      this.loginDTO = new LoginDTO();
    }
  }

  register() {
    this.router.navigate(['/registration'])
  }

  onClick() {
    if (this.loginDTO.email && this.loginDTO.password){
      this.loginService.login(this.loginDTO);
      this.loginService.currentUser.subscribe(
  
        (result) => {
          if (result) {
            location.reload()
            this.router.navigate(['/homepage'])
          }
          else {
            //this.toastr.error('error logging');
          }
        });
        this.router.navigate(['login'])
    }

   

  }

}

