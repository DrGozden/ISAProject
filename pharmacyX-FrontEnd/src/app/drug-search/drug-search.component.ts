import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Drug } from '../model/drug';
import { DrugSpecification } from '../model/drugSpecification';
import { Employee } from '../model/employee';
import { Pharmacy } from '../model/pharmacy';
import { User } from '../model/user';
import { DrugsService } from '../services/drugs.service';
import { LoginService } from '../services/login.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-drug-search',
  templateUrl: './drug-search.component.html',
  styleUrls: ['./drug-search.component.css']
})
export class DrugSearchComponent implements OnInit {

  filteredMedicines: Drug[] = [];
  drugName: string = "";
  drugType: string = "";
  drugTypes: string[] = ["","ANTIBIOTIC", "ANESTHETIC", "ANTIHISTAMINE", "ANTISEPTICS", "STIMULANTS"];
  public currentUser : User;
  public newDrug: Drug = new Drug();
  
  constructor(private drugService: DrugsService, private userService: UserService, private loginService: LoginService) { }

  ngOnInit() {
    const currentUser = this.loginService.currentUserValue;
    this.currentUser = new User();
    this.currentUser = currentUser;
    this.reload();
  }

  public reload() {
    this.newDrug = new Drug();
    this.newDrug.name = "";
    this.newDrug.specification = new DrugSpecification();
    this.newDrug.specification.drugType = "";
    this.newDrug.specification.contraindications = "";
    this.newDrug.code = "";

    this.drugService.loadDrugs().subscribe((data) => {
      this.filteredMedicines = data;
      this.drugName = "";
      this.drugType = "";
      console.log(data);
    });
  }

  public filter() {
    
    this.drugService.filterDrugs(this.drugName,this.drugType).subscribe(data => this.filteredMedicines = data);
  }

  addDrugType(drygT) {
    this.drugType = drygT;
  }

  public addDrug() {
    this.userService.addDrug(this.newDrug).subscribe(data => this.reload());
  }

  public removeDrug(id: number) {
    this.userService.removeDrug(id).subscribe(data => this.reload());
  }

}
