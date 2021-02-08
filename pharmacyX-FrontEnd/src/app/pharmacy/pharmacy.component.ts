import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Drug } from '../model/drug';
import { Pharmacy } from '../model/pharmacy';
import { PredefinedExam } from '../model/predefinedExam';
import { PriceList } from '../model/pricelist';
import { User } from '../model/user';
import { DrugsService } from '../services/drugs.service';
import { LoginService } from '../services/login.service';
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
  drugs: Drug[] = [];
  public currentUser : User;
  pricelistStartDate: string = "";

  constructor(private drugService: DrugsService, private loginService: LoginService, private pharmacyService:PharmacyService,private reservationService:ReservationService,private router: Router, private route: ActivatedRoute, private datePipe: DatePipe) { }

  ngOnInit() {
    const currentUser = this.loginService.currentUserValue;
    this.currentUser = new User();
    this.currentUser = currentUser;
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
          this.drugService.loadDrugs().subscribe((data) => this.drugs = data)
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

  public addDrug(drugId: number, priceListId: number) {
    for(let i = 0; i < this.pharmacy.priceList.length; i++) {
      if(this.pharmacy.priceList[i].id === priceListId) {
        for(let j = 0 ; j < this.drugs.length; j++) {
          if(this.drugs[i].id === drugId) {
            //salji dodavanje drug ida, pricea u cenovnik sa idem na back
          }
        }
      }
    }
  }

  public updateDrug(drugId: number, priceListId: number) {
    for(let i = 0; i < this.pharmacy.priceList.length; i++) {
      if(this.pharmacy.priceList[i].id === priceListId) {
        for(let j = 0 ; j < this.drugs.length; j++) {
          if(this.drugs[i].id === drugId) {
            //salji update drug ida, pricea u cenovnik sa idem na back
          }
        }
      }
    }
  }

  public createPricelist() {
    let pricelistNew = new PriceList();
    pricelistNew.startDate = this.pricelistStartDate;
    this.pharmacyService.createPricelist(pricelistNew).subscribe(data => undefined);
  }

  

}
