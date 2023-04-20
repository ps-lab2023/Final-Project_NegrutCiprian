import {Injectable} from '@angular/core';
import {BehaviorSubject, map, Subject} from 'rxjs';
import {CartItem} from '../../../models/cart-item';
import {HttpClient} from '@angular/common/http';
import {TokenService} from '../../core/services/token.service';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  cartItems: CartItem[] = [];

  totalPrice: Subject<number> = new BehaviorSubject<number>(0);
  totalQuantity: Subject<number> = new BehaviorSubject<number>(0);

  constructor(private httpClient: HttpClient, private tokenService: TokenService) { }
  addToCart(theCartItem: CartItem) {

    let newCartItem: boolean = true;

    if (this.cartItems.length > 0) {
      this.cartItems.forEach(cartItem => {
        if (cartItem.id == theCartItem.id) {
          cartItem.quantity++;
          newCartItem = false;
        }
      })
    }
    if (newCartItem) {
      this.cartItems.push(theCartItem);
    }
    this.computeCartTotals();
  }


  decrementQuantity(theCartItem: CartItem) {
    theCartItem.quantity--;

    if (theCartItem.quantity === 0) {
      this.removeFromCart(theCartItem);
    } else {
      this.computeCartTotals();
    }
  }

  removeAll(){
    this.cartItems = [];
    this.totalPrice.next(0);
    this.totalQuantity.next(0);
  }

  removeFromCart(theCartItem: CartItem) {
    const itemIndex = this.cartItems.findIndex(cardItem => cardItem.id === theCartItem.id);

    if (itemIndex > -1) {
      this.cartItems.splice(itemIndex, 1);
    }
    this.computeCartTotals();
  }

  computeCartTotals() {
    let totalPriceValue: number = 0;
    let totalQuantityValue: number = 0;
    this.cartItems.forEach(cartItem => {
      totalPriceValue += cartItem.price * cartItem.quantity;
      totalQuantityValue += cartItem.quantity;
    });
    this.totalPrice.next(totalPriceValue);
    this.totalQuantity.next(totalQuantityValue);

  }

  placeOrder(){
    return this.httpClient.post<Map<String, any>>(
      "http://localhost:8080/order",
      {
        username: this.tokenService.getUsername(),
        perfumeNames: this.cartItems.map(item=>{
          return item.name;
        }),
      }
    ).pipe(
      map(response => {
        console.log(response);
        return new Map(Object.entries(response));
      })
    );
  }
}
