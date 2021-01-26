import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Drug } from '../model/drug';
import { Pharmacy } from '../model/pharmacy';
import { DrugsService } from '../services/drugs.service';

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
  
  constructor(private drugService: DrugsService) { }

  ngOnInit() {
    this.reload();
  }

  public reload() {
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

}
