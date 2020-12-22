import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { tap } from 'rxjs/operators';
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

getUser(email) {
  return this.http.get<User>(this.userUrl + "/" + email)
    .pipe(tap(
      user => {
        for (var i = 0; i < this.users.length; i++) {
          if (user.email === this.users[i].email) {
            this.users[i] = stop;
            this.userSource.next(this.users);
            return stop;
          }
        }
      })
    )
}

editUser(user: User) {
  this.http.put<User>(this.userUrl, user)
    .subscribe(
      editedUser => {
        for (var i = 0; i < this.users.length; i++) {
          if (editedUser.email === this.users[i].email) {
            this.users[i] = editedUser;
            this.userSource.next(this.users);
            return;
          }
        }
      });
}
}