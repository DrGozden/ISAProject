import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Pharmacy } from '../model/pharmacy';
import { PharmacyService } from '../services/pharmacy.service';

@Component({
  selector: 'app-pharmacies',
  templateUrl: './pharmacies.component.html',
  styleUrls: ['./pharmacies.component.css']
})
export class PharmaciesComponent implements OnInit {

  pharmacies: Pharmacy[] = [];
  filteredPharmacies: Pharmacy[] = [];
  
  constructor(private pharmacyService: PharmacyService) { }

  ngOnInit() {
    this.reload();
  }

  public reload() {
    this.pharmacyService.loadPharmacies().subscribe((data) => {
      this.pharmacies = data;
      this.filteredPharmacies = data;
      console.log(data);
    });
  }

  public refresh(id: number) {  }

}
