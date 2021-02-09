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
import * as moment from 'moment';

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
  public hours: number = 10;

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
    if (this.date === "" ) {
      Swal.fire('Oops...', 'You must fill all fields!', 'error');
    } else if(this.hours < 0 || this.hours > 23) {
      Swal.fire('Oops...', 'Hours must be between 0 and 23!', 'error');
    }else if(this.selectedDrugs.length===0) {
      Swal.fire('Oops...', 'You must select at least one drug!', 'error');
    }
    else {
      let order = new OrderDTO();
      order.supplies = {};
      order.deadline =  this.parsePickerToDate(this.date,this.hours);
      for(let i = 0 ; i < this.selectedDrugs.length; i++) {
        if (this.prices[i] < 1 ) {
          Swal.fire('Oops...', 'Price must be number greater than 0!', 'error');
          return;
        }
        order.supplies[this.selectedDrugs[i].id] = this.prices[i];
      }
      this.userService.createOrder(order).subscribe(data => {
        alert("Order created");      
      });
      
    }
  }

  parsePickerToDate(oldDate: string, hours: number) {
    let parts = oldDate.split("-");
    let newDate = parts[2]+"-"+parts[1]+"-"+parts[0]+" "+hours+":"+"00";
    return newDate;
  }

  addDrug(drug) {
    
    let i;
    let newDrugs = [];
    for (i = 0; i < this.drugs.length; i++) {
      if (this.drugs[i].name === drug) {
        this.selectedDrugs.push(this.drugs[i]);
        this.prices.push(0);
      } else {
        newDrugs.push(this.drugs[i]);
      }
    }
    this.drugs = newDrugs;
    
  }

}
