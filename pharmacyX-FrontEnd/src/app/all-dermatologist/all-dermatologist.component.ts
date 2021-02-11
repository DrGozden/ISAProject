import { Component, OnInit } from '@angular/core';
import { Address } from '../model/address';
import { Employee } from '../model/employee';
import { Pharmacy } from '../model/pharmacy';
import { User } from '../model/user';
import { WorkingHours } from '../model/workingHours';
import { LoginService } from '../services/login.service';
import { PharmacyService } from '../services/pharmacy.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-all-dermatologist',
  templateUrl: './all-dermatologist.component.html',
  styleUrls: ['./all-dermatologist.component.css']
})
export class AllDermatologistComponent implements OnInit {

  filteredDermatologists: User[] = [];
  dermatologistName: string = "";
  public currentUser : User;
  public newEmployee: Employee = new Employee();
  public days: string[] = ["MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY","FRIDAY","SATURDAY","SUNDAY"];
  public starts: number[] = [0,0,0,0,0,0,0];
  public ends: number[] = [0,0,0,0,0,0,0];
  public selectedPharmacy: Pharmacy = new Pharmacy();
  public pharmacies: Pharmacy[] = [];
  public myPharmacy;

  constructor(private userService: UserService, private loginService: LoginService, private pharmacyService: PharmacyService) {
    this.pharmacyService.loadPharmacies().subscribe((data) => {
      this.pharmacies = data;
      console.log(this.pharmacies);
      this.selectedPharmacy = this.pharmacies[0];
    });
  }

  addPharmacy(pharmacy) {
    console.log(pharmacy);
    
    let i;
    for (i = 0; i < this.pharmacies.length; i++) {
      console.log(pharmacy);
      
      if (this.pharmacies[i].name === pharmacy) {
        this.selectedPharmacy = this.pharmacies[i];
        console.log(this.selectedPharmacy);
        this.myPharmacy = this.pharmacies[i];
      }
    }
    console.log(this.selectedPharmacy);
    
  }

  ngOnInit() {
    const currentUser = this.loginService.currentUserValue;
    this.currentUser = new User();
    this.currentUser = currentUser;
    this.reload();
  }

  public reload() {
    this.newEmployee = new Employee();
    this.newEmployee.userRole = "DERMATOLOGIST";
    this.newEmployee.deleted = false;
    this.newEmployee.address = new Address();

    //if user role = pharmacy admin onda izvuci pharmacy ulogovanog pa njegove dermatologe 
    this.userService.filterDermatologists("").subscribe((data) => {
      this.filteredDermatologists = data;
      console.log(data);
    });
  }

  public filter() {
    this.userService.filterDermatologists(this.dermatologistName).subscribe(data => this.filteredDermatologists = data);
  }

  public addEmployee() {
    this.userService.addDermatologist(this.newEmployee).subscribe(data => this.reload());
  }

  public removeEmployee(id: number) {
    console.log(id);
    
    this.userService.removeDermatologist(id).subscribe(data => this.reload(), error => alert("You can not delete employee who has appointments!"));
  }

}
