import { Component, OnInit } from '@angular/core';
import Swal from 'sweetalert2';
import { Address } from '../model/address';
import { Employee } from '../model/employee';
import { Pharmacy } from '../model/pharmacy';
import { User } from '../model/user';
import { WorkingHours } from '../model/workingHours';
import { LoginService } from '../services/login.service';
import { PharmacyService } from '../services/pharmacy.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-all-pharmacist',
  templateUrl: './all-pharmacist.component.html',
  styleUrls: ['./all-pharmacist.component.css']
})
export class AllPharmacistComponent implements OnInit {

  filteredPharmacists: User[] = [];
  pharmacistName: string = "";
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
    this.newEmployee.userRole = "PHARMACIST";
    this.newEmployee.deleted = false;
    this.newEmployee.address = new Address();

    //if user role = pharmacy admin onda izvuci pharmacy ulogovanog pa njegove farmaceute 

    this.userService.filterPharmacists("").subscribe((data) => {
      this.filteredPharmacists = data;
      console.log(data);
    });
  }

  public filter() {
    this.userService.filterPharmacists(this.pharmacistName).subscribe(data => this.filteredPharmacists = data);
  }

  public addEmployee() {
    
    this.userService.addPharmacist(this.newEmployee).subscribe(data => Swal.fire("Succesfuly added new dermatologist!"));
    this.reload()
  }

  public removeEmployee(id: number) {
    this.userService.removePharmacist(id).subscribe(data => this.reload(), error => alert("You can not delete employee who has appointments!"));
  }

}
