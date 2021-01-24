import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Drug } from '../model/drug';
import { Pharmacy } from '../model/pharmacy';

@Injectable({
  providedIn: 'root'
})
export class DrugsService {

  constructor(private http: HttpClient) { }

  public loadDrugs() : Observable<Drug[]>{
    return this.http.get<Drug[]>('http://localhost:9003/drugs'); 
  }

  public loadPharmaciesByDrugId(id: number) : Observable<Pharmacy[]>{
    return this.http.get<Pharmacy[]>('http://localhost:9003/drugs/getByPharmacy/'+id); 
  }

  public reserveDrug(id: number) : Observable<Pharmacy[]>{
    return this.http.get<Pharmacy[]>('http://localhost:9003/drugs/getByPharmacy/'+id); 
  }
}
