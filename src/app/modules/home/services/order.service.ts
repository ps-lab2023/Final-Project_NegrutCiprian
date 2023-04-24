import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http: HttpClient) {}

  getOrders() {
    return this.http.get<any>("http://localhost:8080/order?page=0&size=10&sort=asc")
  }

}
