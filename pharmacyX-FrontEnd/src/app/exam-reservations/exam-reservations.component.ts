import { Component, OnInit } from '@angular/core';
import { Appointment } from '../model/appointment';
import { ReservationService } from '../services/reservation.service';

@Component({
  selector: 'app-exam-reservations',
  templateUrl: './exam-reservations.component.html',
  styleUrls: ['./exam-reservations.component.css']
})
export class ExamReservationsComponent implements OnInit {

  public reservations: Appointment[] = [];

  constructor(private reservationService: ReservationService) {}

  ngOnInit() {
    this.reload();
  }

  public reload(){
    this.reservationService.loadDermatologistReservations().subscribe((data)=> {
      this.reservations = data;
      console.log(this.reservations);
    });
  }

  public cancel(id) {
    this.reservationService.cancelDermatologistReservation(id).subscribe((data)=>{
      this.reload();
    })
  }

  public parseDateTime(d: number[]){
    return new Date(d[0],d[1]-1,d[2],d[3],d[4]);
  }

  public isNotPassed(dateTime: Date) {
    if(dateTime > new Date())
    return true;

    return false;
  }

}
