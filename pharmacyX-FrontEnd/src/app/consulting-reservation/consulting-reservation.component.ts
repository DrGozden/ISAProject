import { Location } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import Swal from 'sweetalert2';
import { Pharmacy } from '../model/pharmacy';
import { User } from '../model/user';
import { FilterDatePharmacy } from '../modelDTO/filterDatePharmacy';
import { ReserveConsultationDTO } from '../modelDTO/reserveConsultationDTO';
import { DrugsService } from '../services/drugs.service';
import { LoginService } from '../services/login.service';
import { PharmacyService } from '../services/pharmacy.service';
import { ReservationService } from '../services/reservation.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-consulting-reservation',
  templateUrl: './consulting-reservation.component.html',
  styleUrls: ['./consulting-reservation.component.css']
})
export class ConsultingReservationComponent implements OnInit {

  public user: User;
  public date: string = "";
  public selectedPharmacy: Pharmacy = new Pharmacy();
  public filteredPharmacies: Pharmacy[] = [];
  public filteredUsers : User[] = [];

  constructor(private userService: UserService, private loginService: LoginService, private location: Location, private toastr: ToastrService,
    private route: ActivatedRoute, private http: HttpClient, private pharmacyService: PharmacyService, private reservationService: ReservationService) {
    
  }

  ngOnInit() {
    this.reload();
  }

  public reload() {
    this.pharmacyService.loadPharmaciesByDateTime(this.date).subscribe((data) => {
      this.filteredPharmacies = data;
      console.log(this.filteredPharmacies);
    });
  }



  

  return() {
    this.location.back()
  }

  addPharmacy(pharmacy) {
    let i;
    for(i = 0 ; i < this.filteredPharmacies.length; i++) {
      if(this.filteredPharmacies[i].name === pharmacy.name) {
        this.selectedPharmacy = this.filteredPharmacies[i];
      }
    }
    let filter = new FilterDatePharmacy();
    filter.dateTime = this.date;
    filter.pharmacyId = this.selectedPharmacy.id;
    console.log(filter);
    this.reservationService.getPharmacistsForDateAndPharmacy(filter).subscribe(data => this.filteredUsers = data);
    
  }

  addPharmacist(pharmacist) {
    this.user = pharmacist;    
  }

  reserve() {
    if (this.date === "") {
      Swal.fire('Oops...', 'You must fill all fields!', 'error');
    }
    else {

    let reser = new ReserveConsultationDTO();
    reser.dateTime = this.date;
    reser.pharmacistId = this.user.id;
    reser.pharmacyId = this.selectedPharmacy.id;
    console.log(reser);
    //this.reservationService.reserveConsultation(reser);
    
          
    }
  }

  

  public refresh(id: number) {
    
  }

}
