import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

private userUrl = "http://localhost:9003/api/user";
private userSource = new BehaviorSubject<User[]>([]);
userObservable = this.userSource.asObservable();
private users = [];

constructor(private http: HttpClient) { }

addUser(user) {
  this.http.post<User>(this.userUrl, user)
    .subscribe(
      addedUser => {
        this.users.push(addedUser);
        this.userSource.next(this.users);
        alert("Succesfully registered user " + user.email + "." + "\n" + "Verification link sent to email!");
      }
    )
}
}