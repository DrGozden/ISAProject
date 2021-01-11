import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Pharmacy } from '../model/pharmacy';
import { PharmacyService } from '../services/pharmacy.service';

@Component({
  selector: 'app-pharmacy',
  templateUrl: './pharmacy.component.html',
  styleUrls: ['./pharmacy.component.css']
})
export class PharmacyComponent implements OnInit {

  pharmacy: Pharmacy = new Pharmacy();
  view: string = "basic";
  //predefinedExaminations: MedicalExaminationHistory[] = [];

  constructor(private pharmacyService:PharmacyService, private route: ActivatedRoute) { }

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
        // this.clinicService.loadPredefinedExaminations(id).subscribe((data) => {
        //   this.predefinedExaminations = data;
        //   console.log(this.predefinedExaminations);
        // });
      });
    });

  }

  public changeView(value: string) {
    console.log(value);
    this.view = value;
  }

  // public reserve(id: number) {
  //   console.log(id);
  //   let userId = localStorage.getItem('id');
  //   this.clinicService.reservePredefinedExaminations(id.toString(),userId).subscribe((data) => {
  //     this.reload();
  //   });
  // }

}
