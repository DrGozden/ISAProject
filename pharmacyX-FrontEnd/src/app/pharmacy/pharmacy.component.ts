import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Pharmacy } from '../model/pharmacy';
import { PredefinedExam } from '../model/predefinedExam';
import { PharmacyService } from '../services/pharmacy.service';
import { ReservationService } from '../services/reservation.service';

@Component({
  selector: 'app-pharmacy',
  templateUrl: './pharmacy.component.html',
  styleUrls: ['./pharmacy.component.css']
})
export class PharmacyComponent implements OnInit {

  pharmacy: Pharmacy = new Pharmacy();
  view: string = "basic";
  predefinedExams: PredefinedExam[] = [];

  constructor(private pharmacyService:PharmacyService,private reservationService:ReservationService,private router: Router, private route: ActivatedRoute, private datePipe: DatePipe) { }

  ngOnInit() {
    this.reload();
  }

  public reload() {;
    this.route.paramMap.subscribe(paramMap => {
      let id = paramMap.get('id');
      console.log(id);
      this.pharmacyService.loadPharmacy(id).subscribe((data) => {
        this.pharmacy = data;
        console.log(this.pharmacy);
        this.pharmacyService.loadPredefinedExams(id).subscribe((data) => {
          this.predefinedExams = data;
          console.log('aaaaaaaaaaaaaaaaaa');
          
          console.log(this.predefinedExams);
        });
      });
    });
  }

  public changeView(value: string) {
    console.log(value);
    this.view = value;
  }

  public parseDateTime(d: number[]){
    return new Date(d[0],d[1]-1,d[2],d[3],d[4]);
  }

  public reserve(id: number) {
    console.log(id);
    
    this.reservationService.reservePredefinedExamination(id.toString()).subscribe((data) => {
      this.reload();
    });
  }

  public reserveMedicine(drug) {
    localStorage.setItem("pharmacyId",this.pharmacy.id.toString());
    this.router.navigate(['drug-reservation',drug.drug.id]);
  }

  

}
