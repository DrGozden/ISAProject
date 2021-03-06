import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DermatologistExam } from '../model/dermatologistExam';
import { Pharmacy } from '../model/pharmacy';
import { PredefinedExam } from '../model/predefinedExam';
import { PriceList } from '../model/pricelist';
import { FreeApointmentDTO } from '../modelDTO/freeAppointmentDTO';
import { PricelistDTO } from '../modelDTO/pricelistDTO';

@Injectable({
  providedIn: 'root'
})
export class PharmacyService {

  constructor(private http: HttpClient) { }

  public loadPharmacy(id: string) : Observable<Pharmacy>{
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

  public createExam(exam: FreeApointmentDTO)  {
    return this.http.post('http://localhost:9003/appointments/create-exam',exam);
  }

  public updatePharmacy(pharmacy: Pharmacy)  {
    console.log(pharmacy);
    
    return this.http.put('http://localhost:9003/pharmacies/update',pharmacy);
  }

  public createPricelist(newPricelist: PricelistDTO)  {
    console.log(newPricelist);
    
    return this.http.post('http://localhost:9003/priceLists',newPricelist);
  }
}
