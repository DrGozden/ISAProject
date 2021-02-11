import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { Drug } from '../model/drug';
import { Employee } from '../model/employee';
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

public decline(vacationReason: VacationReason): Observable<any> {
  console.log(vacationReason);
  
  return this.http.put('http://localhost:9003/reject-vacation',vacationReason);
}

public loadAllOrders(): Observable<OrderDTO[]> {
  return this.http.get<OrderDTO[]>('http://localhost:9003/supplies/orders');
}

public getOffersForOrder(orderId: number): Observable<SupplierOffer[]> {
  console.log('http://localhost:9003/supplies/pending-offers/'+orderId);
  
  return this.http.get<SupplierOffer[]>('http://localhost:9003/supplies/pending-offers/'+orderId);
}

public acceptOffer(offerId: number) : Observable<any> {
  return this.http.post('http://localhost:9003/supplies/offers/'+offerId+'/accept', {});
}

public addDermatologist(employee: Employee) : Observable<any> {
  return this.http.post('http://localhost:9003/pharmacies/add-dermatologist', employee);
}

public addPharmacist(employee: Employee) : Observable<any> {
  console.log(employee);
  
  return this.http.post('http://localhost:9003/pharmacies/add-pharmacist', employee);
}

public removePharmacist(id: number) : Observable<any> {
  return this.http.delete('http://localhost:9003/pharmacies/remove-pharmacist/'+id);
}

public removeDermatologist(id: number) : Observable<any> {
  return this.http.delete('http://localhost:9003/pharmacies/remove-dermatologist/'+id);
}

public addDrug(drug: Drug) : Observable<any> {
  return this.http.post('http://localhost:9003/drugs', drug);
}

public removeDrug(id: number) : Observable<any> {
  return this.http.delete('http://localhost:9003/pharmacies/remove-pharmacist/'+id);
}

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

changePassword(user: User) {
  this.http.post<User>("http://localhost:9003/me/password-change", user)
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