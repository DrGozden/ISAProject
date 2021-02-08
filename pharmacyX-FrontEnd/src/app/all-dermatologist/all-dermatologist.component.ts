import { Component, OnInit } from '@angular/core';
import { Address } from '../model/address';
import { Employee } from '../model/employee';
import { User } from '../model/user';
import { LoginService } from '../services/login.service';
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
  
  constructor(private userService: UserService, private loginService: LoginService) { }

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
    this.userService.removeDermatologist(id).subscribe(data => this.reload());
  }

}
