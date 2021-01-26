import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Drug } from '../model/drug';
import { DrugReservation } from '../model/drugReservation';
import { Pharmacy } from '../model/pharmacy';
import { DrugReservationDTO } from '../modelDTO/drugReservationDTO';

@Injectable({
  providedIn: 'root'
})
export class DrugsService {

  constructor(private http: HttpClient) { }

  public loadDrugs() : Observable<Drug[]>{
    return this.http.get<Drug[]>('http://localhost:9003/drugs'); 
  }

  public loadPharmaciesByDrugId(id: number) : Observable<Pharmacy[]>{
    console.log('http://localhost:9003/pharmacies/containingDrug/'+id);
    
    return this.http.get<Pharmacy[]>('http://localhost:9003/pharmacies/containingDrug/'+id); 
  }

  public reserveDrug(reservation: DrugReservationDTO) : Observable<DrugReservation>{
    return this.http.post<DrugReservation>('http://localhost:9003/drug_reservation',reservation); 
  }

  public filterDrugs(name: string, drugType: string) : Observable<Drug[]> {
    if(drugType === "") {
      return this.http.get<Drug[]>('http://localhost:9003/drugs/search',{
        params: new HttpParams().set('search', name)
      });
    } else {
      return this.http.get<Drug[]>('http://localhost:9003/drugs/search',{
        params: new HttpParams().set('search', name).set('filter',drugType)
      });
    }
    
  }
}
