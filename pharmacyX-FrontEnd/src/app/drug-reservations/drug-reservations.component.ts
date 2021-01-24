import { Component, OnInit } from '@angular/core';
import { DrugReservation } from '../model/drugReservation';
import { ReservationService } from '../services/reservation.service';

@Component({
  selector: 'app-drug-reservations',
  templateUrl: './drug-reservations.component.html',
  styleUrls: ['./drug-reservations.component.css']
})
export class DrugReservationsComponent implements OnInit {

  public reservations: DrugReservation[] = [];

  constructor(private reservationService: ReservationService) {}

  ngOnInit() {
    this.reload();
  }

  public reload(){
    this.reservationService.loadDrugReservations().subscribe((data)=> {
      this.reservations = data;
      console.log(this.reservations);
    });
  }

  public cancel(id) {
    this.reservationService.cancelDrugReservation(id).subscribe((data)=>{
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
