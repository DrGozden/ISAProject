import { Component, OnInit } from '@angular/core';
import { OrderDTO } from '../modelDTO/orderDTO';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-order-pending',
  templateUrl: './order-pending.component.html',
  styleUrls: ['./order-pending.component.css']
})
export class OrderPendingComponent implements OnInit {

  public orders: OrderDTO[] = []; // bice drugi obj
  
  constructor(private userService: UserService) {}

  ngOnInit() {
    this.reload();
  }

  public reload(){
    this.userService.loadAllOrders().subscribe((data)=> {
      this.orders = data;
      console.log(this.orders);
    });
  }

  public parseDateTime(d: number[]){
    return new Date(d[0],d[1]-1,d[2],d[3],d[4]);
  }

  public accept(offerId: number) {
    this.userService.acceptOffer(offerId).subscribe((data)=>{
      this.reload();
    })
  }

  public getOffersForOrder(orderId: number){
    console.log(orderId);
    //this.userService.getOffersForOrder(orderId).subscribe((data) => {return data});
  }
}
