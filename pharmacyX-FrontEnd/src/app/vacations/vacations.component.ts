import { Component, OnInit } from '@angular/core';
import { DrugReservation } from '../model/drugReservation';
import { Vacation } from '../model/vacation';
import { VacationUser } from '../model/vacatonUser';
import { VacationReason } from '../modelDTO/vacationReason';
import { ReservationService } from '../services/reservation.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-vacations',
  templateUrl: './vacations.component.html',
  styleUrls: ['./vacations.component.css']
})
export class VacationsComponent implements OnInit {

  public vacations: VacationUser[] = [];
  public reason: string = "";

  constructor(private userService: UserService) {}

  ngOnInit() {
    this.reload();
  }

  public reload(){
    this.userService.loadVacations().subscribe((data)=> {
      this.vacations = data;
      console.log(this.vacations);
    });
  }

  public accept(id) {
    console.log(id);
    
    this.userService.accept(id).subscribe((data)=>{
      this.reload();
    })
  }

  public decline(id) {
    console.log(id);
    let reasonDTO = new VacationReason();
    reasonDTO.vacationId = id;
    reasonDTO.rejectDescription = this.reason;

    this.userService.decline(id, reasonDTO).subscribe((data)=>{
      this.reload();
    })
  }

  public parseDateTime(d: number[]){
    return new Date(d[0],d[1]-1,d[2],d[3],d[4]);
  }

  public getState(deleted: boolean, accepted: boolean) {
    if(deleted) return "Rejected!";
    if(accepted) return "Accepted!";
    return "Waiting for approval";
  }

}
