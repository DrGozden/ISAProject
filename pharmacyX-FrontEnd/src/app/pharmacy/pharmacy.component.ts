import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Drug } from '../model/drug';
import { Pharmacy } from '../model/pharmacy';
import { PredefinedExam } from '../model/predefinedExam';
import { PriceList } from '../model/pricelist';
import { User } from '../model/user';
import { PricelistDTO } from '../modelDTO/pricelistDTO';
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
  newDrugs: Drug[] = [];
  newPrices: number[] = [];
  selectedDrug:string;
  newPrice: number = 0;

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
        this.newDrugs = [];
        this.newPrices = [];
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

  /*public addDrug(drugId: number, priceListId: number) {
    for(let i = 0; i < this.pharmacy.priceList.length; i++) {
      if(this.pharmacy.priceList[i].id === priceListId) {
        for(let j = 0 ; j < this.drugs.length; j++) {
          if(this.drugs[i].id === drugId) {
            //salji dodavanje drug ida, pricea u cenovnik sa idem na back
          }
        }
      }
    }
  }*/

  addDrug(drug) {
    let i;
    for(i = 0 ; i < this.pharmacy.drugsInStock.length; i++) {
      if(this.pharmacy.drugsInStock[i].drug.name === drug) {
        //this.selectedDrug = this.pharmacy.drugsInStock[i].drug;
      }
    }
  }

  public addDrugToPricelist() {
    let dr = new Drug();
    // dr.id = this.selectedDrug.id;
    
    let i;
    for(i = 0 ; i < this.pharmacy.drugsInStock.length; i++) {
      if(this.pharmacy.drugsInStock[i].drug.name === this.selectedDrug) {
        dr.id = this.pharmacy.drugsInStock[i].drug.id;
        dr.name =this.pharmacy.drugsInStock[i].drug.name;
      }
    }
    console.log(dr);
    console.log(this.newPrice);

    
    this.newDrugs.push(dr);
    this.newPrices.push(this.newPrice);
    this.selectedDrug = "";
    this.newPrice = 0;
  }

  public removeFromList(index: number) {
    let temp = JSON.parse(JSON.stringify(this.newPrices));
    let temp2 = JSON.parse(JSON.stringify(this.newDrugs));
    this.newPrices = [];
    this.newDrugs = [];
    for(let i = 0 ; i < temp.length; i++) {
      if(i !== index) {
        this.newPrices.push(temp[i]);
        this.newDrugs.push(temp2[i]);
      }
    }
  }

  public createPricelist() {
    let pricelistNew = new PricelistDTO();
    pricelistNew.startDate = this.pricelistStartDate;
    pricelistNew.drugs = this.newDrugs;
    pricelistNew.pricesList = this.newPrices;
    this.newDrugs = [];
    this.newPrices = [];
    this.pharmacyService.createPricelist(pricelistNew).subscribe(data => {this.reload()});
  }

  

}
