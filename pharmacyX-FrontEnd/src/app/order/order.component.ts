import { Component, OnInit } from '@angular/core';
import Swal from 'sweetalert2';
import { Drug } from '../model/drug';
import { Pharmacy } from '../model/pharmacy';
import { User } from '../model/user';
import { FreeApointmentDTO } from '../modelDTO/freeAppointmentDTO';
import { OrderDTO } from '../modelDTO/orderDTO';
import { DrugsService } from '../services/drugs.service';
import { PharmacyService } from '../services/pharmacy.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {

  public user: User;
  public date: string = "";
  public selectedDrugs: Drug[] = [];
  public prices: number[] = [];
  public drugs: Drug[] = [];
  public selectedDrug: Drug = new Drug();

  constructor(private userService: UserService, private drugService: DrugsService) {
    this.user = new User();
    this.drugService.loadDrugs().subscribe((data) => {
      this.drugs = data;
      console.log(this.drugs);
    });
  }

  ngOnInit() {
    this.userService.getUser().subscribe((data) => {
      this.user = data;
    });
  }

  create() {
    if (this.date === "") {
      Swal.fire('Oops...', 'You must fill all fields!', 'error');
    }
    else {
      let order = new OrderDTO();
      order.supplies = {};
      order.deadline = this.date;
      for(let i = 0 ; i < this.selectedDrugs.length; i++) {
        order.supplies[this.selectedDrugs[i].name] = this.prices[i];
      }
      console.log(order);
      this.userService.createOrder(order).subscribe(data => undefined);

      //this.return();      
    }
  }

  return() {
    //this.location.back()
  }

  addDrug(drug) {
    
    let i;
    for (i = 0; i < this.drugs.length; i++) {
      if (this.drugs[i].name === drug) {
        this.selectedDrugs.push(this.drugs[i]);
        this.prices.push(0);
      }
    }
  }

}
