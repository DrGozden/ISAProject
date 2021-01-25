import { Component, OnInit } from '@angular/core';
import { User } from '../model/user';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-all-dermatologist',
  templateUrl: './all-dermatologist.component.html',
  styleUrls: ['./all-dermatologist.component.css']
})
export class AllDermatologistComponent implements OnInit {

  filteredDermatologists: User[] = [];
  dermatologistName: string = "";
  
  constructor(private userService: UserService) { }

  ngOnInit() {
    this.reload();
  }

  public reload() {
    this.userService.filterDermatologists("").subscribe((data) => {
      this.filteredDermatologists = data;
      console.log(data);
    });
  }

  public filter() {
    this.userService.filterDermatologists(this.dermatologistName).subscribe(data => this.filteredDermatologists = data);
  }

}
