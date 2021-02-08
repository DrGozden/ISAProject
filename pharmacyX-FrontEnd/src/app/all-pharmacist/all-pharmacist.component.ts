import { Component, OnInit } from '@angular/core';
import { Address } from '../model/address';
import { Employee } from '../model/employee';
import { User } from '../model/user';
import { LoginService } from '../services/login.service';
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
  
  constructor(private userService: UserService, private loginService: LoginService) { }

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
    this.userService.addPharmacist(this.newEmployee).subscribe(data => this.reload());
  }

  public removeEmployee(id: number) {
    this.userService.removePharmacist(id).subscribe(data => this.reload());
  }

}
