import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Pharmacy } from '../model/pharmacy';

@Component({
  selector: 'app-pharmacies',
  templateUrl: './pharmacies.component.html',
  styleUrls: ['./pharmacies.component.css']
})
export class PharmaciesComponent implements OnInit {

  pharmacies: Pharmacy[] = [];
  filteredPharmacies: Pharmacy[] = [];
  
  constructor(private http: HttpClient) { }

  ngOnInit() {
    this.reload();
  }

  public reload() {
    this.http.get<Pharmacy[]>('http://localhost:9003/pharmacies').subscribe((data) => {
      this.pharmacies = data;
      this.filteredPharmacies = data;
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
