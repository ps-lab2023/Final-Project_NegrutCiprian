import { Injectable } from '@angular/core';
import {CartItem} from "../../../models/cart-item";
import {BehaviorSubject, Subject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class FavoriteService {

  favorites: CartItem[] = [];
  totalPrice: Subject<number> = new BehaviorSubject<number>(0);
  totalQuantity: Subject<number> = new BehaviorSubject<number>(0);

  constructor() { }

  addToFavorite(theFavItem: CartItem) {
    console.log(theFavItem);
    let newCartItem: boolean = true;

    if (this.favorites.length > 0) {
      this.favorites.forEach(cartItem => {
        if (cartItem.id == theFavItem.id) {
          cartItem.quantity++;
          newCartItem = false;
        }
      })
    }
    if (newCartItem) {
      this.favorites.push(theFavItem);
    }
    this.computeCartTotals();
  }

  computeCartTotals() {
    let totalPriceValue: number = 0;
    let totalQuantityValue: number = 0;
    this.favorites.forEach(cartItem => {
      totalPriceValue += cartItem.price * cartItem.quantity;
      totalQuantityValue += cartItem.quantity;
    });
    this.totalPrice.next(totalPriceValue);
    this.totalQuantity.next(totalQuantityValue);
  }

  removeFromCart(theCartItem: CartItem) {
    const itemIndex = this.favorites.findIndex(cardItem => cardItem.id === theCartItem.id);

    if (itemIndex > -1) {
      this.favorites.splice(itemIndex, 1);
    }
    this.computeCartTotals();
  }

  removeAll(){
    this.favorites = [];
    this.totalPrice.next(0);
    this.totalQuantity.next(0);
  }

}
