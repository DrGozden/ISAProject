import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { SupplierOffer } from '../model/supplierOffer';
import { User } from '../model/user';
import { Vacation } from '../model/vacation';
import { VacationUser } from '../model/vacatonUser';
import { OrderDTO } from '../modelDTO/orderDTO';
import { VacationReason } from '../modelDTO/vacationReason';

@Injectable({
  providedIn: 'root'
})
export class UserService {


private userSource = new BehaviorSubject<User[]>([]);
userObservable = this.userSource.asObservable();
private users = [];

constructor(private http: HttpClient) { }

addUser(user) {
  this.http.post<User>("http://localhost:9003/register", user)
    .subscribe(
      addedUser => {
        this.users.push(addedUser);
        this.userSource.next(this.users);
        alert("Succesfully registered user " + user.email + "." + "\n" + "Verification link sent to email!");
      }
    )
}

public getUser() : Observable<User>{
  //let headers = this.authService.getHeaders();
  return this.http.get<User>('http://localhost:9003/me'); 
}

public filterPharmacists(name: string) : Observable<User[]> {
  return this.http.get<User[]>('http://localhost:9003/pharmacists/search',{
    params: new HttpParams().set('search', name)
  });
}

public filterDermatologists(name: string) : Observable<User[]> {
  return this.http.get<User[]>('http://localhost:9003/dermatologists/search',{
    params: new HttpParams().set('search', name)
  });
}

public loadVacations() : Observable<VacationUser[]> {
  return this.http.get<VacationUser[]>('http://localhost:9003/vacation-requests');
}

public accept(id: number) : Observable<any> {
  return this.http.put('http://localhost:9003/approve-vacation/'+id, {});
}

public createOrder(order: OrderDTO) : Observable<any> {
  return this.http.post('http://localhost:9003/supplies/create-order', order);
}

public decline(id: number,reason: VacationReason): Observable<any> {
  return this.http.put('http://localhost:9003/reject-vacation/'+id,{});
}

public loadAllOrders(): Observable<OrderDTO[]> {
  return this.http.get<OrderDTO[]>('http://localhost:9003/supplies/orders');
}

public getOffersForOrder(orderId: number): Observable<SupplierOffer[]> {
  return this.http.get<SupplierOffer[]>('http://localhost:9003/supplies/pending-offers/'+orderId);
}

public acceptOffer(offerId: number) : Observable<any> {
  return this.http.put('http://localhost:9003/supplies/offers/'+offerId+'/accept', {});
}

// getUser(email) {
//   return this.http.get<User>("http://localhost:9003/user" + "/" + email)
//     .pipe(tap(
//       user => {
//         for (var i = 0; i < this.users.length; i++) {
//           if (user.email === this.users[i].email) {
//             this.users[i] = stop;
//             this.userSource.next(this.users);
//             return stop;
//           }
//         }
//       })
//     )
// }

editUser(user: User) {
  this.http.put<User>("http://localhost:9003/me", user)
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