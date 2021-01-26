import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Pharmacy } from '../model/pharmacy';
import { PredefinedExam } from '../model/predefinedExam';

@Injectable({
  providedIn: 'root'
})
export class PharmacyService {

  constructor(private http: HttpClient) { }

  public loadPharmacy(id: string) : Observable<Pharmacy>{
    //let headers = this.authService.getHeaders();
    return this.http.get<Pharmacy>('http://localhost:9003/pharmacies/' + id); 
  }

  public loadPharmacies() : Observable<Pharmacy[]>{
    return this.http.get<Pharmacy[]>('http://localhost:9003/pharmacies' ); 
  }

  public loadPharmaciesByDateTime(date: string) : Observable<Pharmacy[]>{
    return this.http.get<Pharmacy[]>('http://localhost:9003/pharmacies' );//ovde dodati i date i time 
  }

  public loadPredefinedExams(id: string) : Observable<PredefinedExam[]>{
    return this.http.get<PredefinedExam[]>('http://localhost:9003/appointments/pharmacies/'+id+'/unreserved_exams' ); 
  }

  public filterPharmacies(name: string, rating: string) : Observable<Pharmacy[]> {
    return this.http.get<Pharmacy[]>('http://localhost:9003/pharmacies/search',{
      params: new HttpParams().set('search', name).set('rating',rating)
    });
  }
}
