import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Appointment } from '../model/appointment';
import { DermatologistExam } from '../model/dermatologistExam';
import { DrugReservation } from '../model/drugReservation';
import { PharmacistExam } from '../model/pharmacistExam';
import { User } from '../model/user';
import { FilterDatePharmacy } from '../modelDTO/filterDatePharmacy';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  constructor(private http: HttpClient) { }

  public loadDermatologistReservations() : Observable<Appointment[]>{
    return this.http.get<Appointment[]>('http://localhost:9003/appointments/my-exams'); 
  }

  public loadPharmacistConsultations() : Observable<Appointment[]>{
    return this.http.get<Appointment[]>('http://localhost:9003/appointments/my-consultations'); 
  }

  public loadDrugReservations() : Observable<DrugReservation[]>{
    return this.http.get<DrugReservation[]>('http://localhost:9003/me/history/drugreservations'); 
  }

  public cancelDermatologistReservation(id:string) : Observable<DermatologistExam>{
    return this.http.put<DermatologistExam>('http://localhost:9003/appointments/exams/'+id+"/cancel",{});
  }

  public cancelPharmacistReservation(id:string) : Observable<PharmacistExam>{
    return this.http.put<PharmacistExam>('http://localhost:9003/appointments/consultations/'+id+"/cancel",{}); 
  }

  public cancelDrugReservation(id:string) : Observable<DrugReservation>{
    return this.http.put<DrugReservation>('http://localhost:9003/drug_reservation/'+id+"/cancel",{}); 
  }

  public reservePredefinedExamination(id:string) : Observable<DermatologistExam>{
    return this.http.post<DermatologistExam>('http://localhost:9003/appointments/exams/'+id,{});
  }

  public getPharmacistsForDateAndPharmacy(filter:FilterDatePharmacy) : Observable<User[]>{
    return this.http.post<User[]>('http://localhost:9003/pharmacies/availablePharmacist',filter);
  }
  
}
