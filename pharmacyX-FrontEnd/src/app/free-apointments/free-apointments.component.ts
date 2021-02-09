import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import Swal from 'sweetalert2';
import { Pharmacy } from '../model/pharmacy';
import { User } from '../model/user';
import { DrugReservationDTO } from '../modelDTO/drugReservationDTO';
import { FreeApointmentDTO } from '../modelDTO/freeAppointmentDTO';
import { LoginService } from '../services/login.service';
import { PharmacyService } from '../services/pharmacy.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-free-apointments',
  templateUrl: './free-apointments.component.html',
  styleUrls: ['./free-apointments.component.css']
})
export class FreeApointmentsComponent implements OnInit {

  public user: User;
  public date: string = "";
  public price: number = 0;
  public selectedPharmacy: Pharmacy = new Pharmacy();
  public pharmacies: Pharmacy[] = [];
  public selectedDermatologist: User = new User();
  public dermatologists: User[] = [];
  public hours: number = 10;

  public myPharmacy;
  public myDermatologist;


  constructor(private userService: UserService, private pharmacyService: PharmacyService) {
    this.user = new User();
    this.pharmacyService.loadPharmacies().subscribe((data) => {
      this.pharmacies = data;
      console.log(this.pharmacies);
      this.selectedPharmacy = this.pharmacies[0];
      this.userService.filterDermatologists("").subscribe((data) => {
        this.dermatologists = data;
        this.selectedDermatologist = this.dermatologists[0];
        console.log(this.dermatologists);
      });
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

  create() {
    if (this.date === "" || this.price === 0) {
      Swal.fire('Oops...', 'You must fill all fields!', 'error');
    } else if(this.hours < 0 || this.hours > 23) {
      Swal.fire('Oops...', 'Hours must be between 0 and 23!', 'error');
    } else {
      let newAppointment = new FreeApointmentDTO();
      newAppointment.price = this.price;
      newAppointment.dateStart = this.parsePickerToDate(this.date,this.hours);
      
      console.log(this.myDermatologist);
      console.log(this.myPharmacy);
      
      newAppointment.pharmacyId = this.selectedPharmacy.id;
      newAppointment.dermatologistId = this.selectedDermatologist.id;
      console.log(newAppointment);
      this.pharmacyService.createExam(newAppointment).subscribe(data => {
        alert("Success!");
      });

      //this.return();      
    }
  }

  return() {
    //this.location.back()
  }

  addPharmacy(pharmacy) {
    console.log(pharmacy);
    
    let i;
    for (i = 0; i < this.pharmacies.length; i++) {
      console.log(pharmacy);
      
      if (this.pharmacies[i].name === pharmacy) {
        this.selectedPharmacy = this.pharmacies[i];
        console.log(this.selectedPharmacy);
        this.myPharmacy = this.pharmacies[i];
      }
    }
    console.log(this.selectedPharmacy);
    
  }

  addDermatologist(dermatologist: string) {
    let i;
    for (i = 0; i < this.dermatologists.length; i++) {
      if (this.dermatologists[i].firstName === dermatologist.split(' ')[0] && this.dermatologists[i].lastName === dermatologist.split(' ')[1]) {
        this.selectedDermatologist = this.dermatologists[i];
        this.myDermatologist = this.dermatologists[i];
      }
    }
  }

  

}
