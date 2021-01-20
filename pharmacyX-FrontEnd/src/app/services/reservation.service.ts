import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Appointment } from '../model/appointment';
import { DermatologistExam } from '../model/dermatologistExam';
import { PharmacistExam } from '../model/pharmacistExam';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  constructor(private http: HttpClient) { }

  public loadDermatologistReservations() : Observable<Appointment[]>{
    return this.http.get<Appointment[]>('http://localhost:9003/me/history/exams'); 
  }

  public loadPharmacistConsultations() : Observable<Appointment[]>{
    return this.http.get<Appointment[]>('http://localhost:9003/me/history/consultations'); 
  }

  public cancelDermatologistReservation(id:string) : Observable<DermatologistExam>{
    return this.http.put<DermatologistExam>('http://localhost:9003/appointmnents/exams/'+id+"/cancel",{}); //mozda ne
  }

  public cancelPharmacistReservation(id:string) : Observable<PharmacistExam>{
    return this.http.put<PharmacistExam>('http://localhost:9003/appointmnents/consultations/'+id+"/cancel",{}); //mozda ne
  }
  
}
