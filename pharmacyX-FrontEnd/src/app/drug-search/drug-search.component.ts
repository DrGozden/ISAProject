import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Drug } from '../model/drug';
import { Pharmacy } from '../model/pharmacy';

@Component({
  selector: 'app-drug-search',
  templateUrl: './drug-search.component.html',
  styleUrls: ['./drug-search.component.css']
})
export class DrugSearchComponent implements OnInit {

  medicines: Drug[] = [];
  filteredMedicines: Drug[] = [];
  
  constructor(private http: HttpClient) { }

  ngOnInit() {
    this.reload();
  }

  public reload() {
    this.http.get<Drug[]>('http://localhost:9003/pharmacies').subscribe((data) => {
      this.medicines = data;
      this.filteredMedicines = data;
      console.log(data);
      
      /*this.http.get<LocationDTO[]>('http://localhost:8080/api/location/allLocation').subscribe((data) => {
        this.locations = data;
        console.log(data);
      });*/
    });
  }

  public refresh(id: number) {
    /*var i;
    this.filteredManifestations = [];
    for (i = 0; i < this.manifestations.length; i++) {
      if (this.manifestations[i].locationId == id || id == 0) {
        this.filteredManifestations.push(this.manifestations[i]);
      }
    }

    if(id == 0) {
      this.selectedLocation = new LocationDTO();
      this.selectedLocation.locationName == "No filter";
    } else {
      var j;
      for (j = 0; j < this.locations.length; j++) {
        if (this.locations[j].id == id) {
          console.log(this.locations[j]);
          this.selectedLocation = this.locations[j];
        }
      }
    }*/
  }

}
