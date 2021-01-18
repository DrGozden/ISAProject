import { Injectable } from '@angular/core';
import { LoginDTO } from '../modelDTO/loginDTO';
import { User } from '../model/user';
import { map } from 'rxjs/operators';
import { BehaviorSubject, Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import Swal from 'sweetalert2/dist/sweetalert2.js';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  //private userUrl = "http://localhost:8080/api/login";
  private currentUserSubject: BehaviorSubject<User>;
  public currentUser: Observable<User>;
  //private http: HttpClient;

  constructor(private http : HttpClient, private router: Router) {
    this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('currentUser')));
    this.currentUser = this.currentUserSubject.asObservable();
  }

  //Registracija TO-DO
  register(user: User) {
    //return this.http.post(`${environment.apiUrl}/register`, user);
}

  public get currentUserValue(): User {
    let user: User = new User();
    if (localStorage.getItem('currentUser')) {
      user.deserialize(JSON.parse(localStorage.getItem('currentUser')));
      if (user.id != this.currentUserSubject.value.id) {
        this.currentUserSubject.next(user);
      }
    }
    return this.currentUserSubject.value;
  }

  
  login(loginDto: LoginDTO) {
    return this.http.post<any>(`http://localhost:9003/login`, loginDto)
      .pipe(map(userDTO => {
        if (userDTO && userDTO.jwttoken) {
          // store user details and jwt token in local storage to keep user logged in between page refreshes
          let user: User = new User().deserialize(userDTO);
          localStorage.setItem('currentUser', JSON.stringify(user));
          this.currentUserSubject.next(user);
        }
        return userDTO;
      })).subscribe(
        (data) => { Swal.fire({text: 'Hello!',icon: 'success'})},
        error => {  Swal.fire('Oops...', 'Bad email/password!', 'error') }
      );
  }
  

  logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('currentUser');
    this.currentUserSubject.next(null);
    this.router.navigate(["/login"]);
  }

}

