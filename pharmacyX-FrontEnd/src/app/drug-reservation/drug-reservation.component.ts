import { Location } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import Swal from 'sweetalert2';
import { Drug } from '../model/drug';
import { DrugReservation } from '../model/drugReservation';
import { Pharmacy } from '../model/pharmacy';
import { User } from '../model/user';
import { DrugReservationDTO } from '../modelDTO/drugReservationDTO';
import { DrugsService } from '../services/drugs.service';
import { LoginService } from '../services/login.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-drug-reservation',
  templateUrl: './drug-reservation.component.html',
  styleUrls: ['./drug-reservation.component.css']
})
export class DrugReservationComponent implements OnInit {

  public user: User;
  public drugId: number;
  public date: string = "";
  public selectedPharmacy: Pharmacy = new Pharmacy();
  public pharmacies: Pharmacy[] = [];
  public pharmacyId: string = "";
  public hours: number = 10;

  constructor(private userService: UserService, private loginService: LoginService, private location: Location, private toastr: ToastrService,
    private route: ActivatedRoute, private http: HttpClient, private drugService: DrugsService) {
    this.user = new User();
    this.route.paramMap.subscribe(paramMap => {
      this.drugId = +paramMap.get('id');
      this.drugService.loadPharmaciesByDrugId(this.drugId).subscribe((data) => {
        this.pharmacies = data;
        console.log(this.pharmacies);
        if(localStorage.getItem("pharmacyId")){
          let i;
          for(i = 0; i < this.pharmacies.length; i++) {
            if(this.pharmacies[i].id === +localStorage.getItem("pharmacyId")) {
             this.selectedPharmacy = this.pharmacies[i];
            }
          }
        }
      })  
    });
  }

  ngOnInit() {
    this.userService.getUser().subscribe((data) => {
      this.user = data;
    });
  }

  parsePickerToDate(oldDate: string, hours: number) {
    let parts = oldDate.split("-");
    let newDate = parts[2]+"-"+parts[1]+"-"+parts[0]+" "+hours+":"+"00";
    return newDate;
  }


  reserve() {
    if (this.date === "" ) {
      Swal.fire('Oops...', 'You must fill all fields!', 'error');
    } else if(this.hours < 0 || this.hours > 23) {
      Swal.fire('Oops...', 'Hours must be between 0 and 23!', 'error');
    } else if(this.selectedPharmacy.id === 0) {
      Swal.fire('Oops...', 'Please select pharmacy!', 'error');
    }
    else {
      let reservation = new DrugReservationDTO();
      reservation.drugId = this.drugId;
      reservation.pharmacyId = this.selectedPharmacy.id;
      reservation.deadlineDateTime = this.parsePickerToDate(this.date,this.hours);
      console.log(reservation);
      this.drugService.reserveDrug(reservation).subscribe(data => Swal.fire("Succesful reservation!"));
      
      //this.return();      
    }
  }

  return() {
    this.location.back()
  }

  addPharmacy(pharmacy) {
    let i;
    for(i = 0 ; i < this.pharmacies.length; i++) {
      if(this.pharmacies[i].name === pharmacy) {
        this.selectedPharmacy = this.pharmacies[i];
      }
    }
  }

}
