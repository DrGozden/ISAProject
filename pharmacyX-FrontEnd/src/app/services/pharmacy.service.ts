import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Pharmacy } from '../model/pharmacy';

@Injectable({
  providedIn: 'root'
})
export class PharmacyService {

  constructor(private http: HttpClient) { }

  public loadPharmacy(id: string) : Observable<Pharmacy>{
    //let headers = this.authService.getHeaders();
    return this.http.get<Pharmacy>('http://localhost:9003/pharmacy/' + id); 
  }
}