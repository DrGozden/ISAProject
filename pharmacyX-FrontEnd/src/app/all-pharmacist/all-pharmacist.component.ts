import { Component, OnInit } from '@angular/core';
import { User } from '../model/user';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-all-pharmacist',
  templateUrl: './all-pharmacist.component.html',
  styleUrls: ['./all-pharmacist.component.css']
})
export class AllPharmacistComponent implements OnInit {

  filteredPharmacists: User[] = [];
  pharmacistName: string = "";
  
  constructor(private userService: UserService) { }

  ngOnInit() {
    this.reload();
  }

  public reload() {
    this.userService.filterPharmacists("").subscribe((data) => {
      this.filteredPharmacists = data;
      console.log(data);
    });
  }

  public filter() {
    this.userService.filterPharmacists(this.pharmacistName).subscribe(data => this.filteredPharmacists = data);
  }

}
