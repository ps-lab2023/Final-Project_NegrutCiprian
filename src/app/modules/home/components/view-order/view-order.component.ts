import {Component, OnInit} from '@angular/core';
import {OrderService} from "../../services/order.service";

@Component({
  selector: 'app-view-order',
  templateUrl: './view-order.component.html',
  styleUrls: ['./view-order.component.scss']
})
export class ViewOrderComponent implements OnInit{

    orders: any[] = []

    constructor(private orderService: OrderService) {}

    ngOnInit() {
      this.getOrders();
    }

    private getOrders() {
      this.orderService.getOrders().subscribe((response) => {
        this.orders = response.content;
      })
    }

}
