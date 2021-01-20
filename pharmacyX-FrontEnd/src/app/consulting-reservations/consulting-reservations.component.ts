import { Component, OnInit } from '@angular/core';
import { Appointment } from '../model/appointment';
import { ReservationService } from '../services/reservation.service';

@Component({
  selector: 'app-consulting-reservations',
  templateUrl: './consulting-reservations.component.html',
  styleUrls: ['./consulting-reservations.component.css']
})
export class ConsultingReservationsComponent implements OnInit {

  public reservations: Appointment[] = [];

  constructor(private reservationService: ReservationService) {}

  ngOnInit() {
    this.reload();
  }

  public reload(){
    this.reservationService.loadPharmacistConsultations().subscribe((data)=> {
      this.reservations = data;
      console.log(this.reservations);
    });
  }

  public cancel(id) {
    this.reservationService.cancelPharmacistReservation(id).subscribe((data)=>{
      this.reload();
    })
  }

}
