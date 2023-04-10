import { Component } from '@angular/core';
import { CartItem } from 'src/app/models/cart-item';
import { CartService } from '../home/services/cart-service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cart-details',
  templateUrl: './cart-details.html',
})

export class CartDetailsComponent {

  cartItems: CartItem[] = [];
  totalPrice: number = 0;
  totalQuantity: number = 0;

  constructor(private cartService: CartService, private router: Router) { }

  ngOnInit(): void {
    this.listCartDetails();
  }

  listCartDetails() {

    this.cartItems = this.cartService.cartItems;

    this.cartService.totalPrice.subscribe(
      data => this.totalPrice = data
    );

    this.cartService.totalQuantity.subscribe(
      data => this.totalQuantity = data
    );

    this.cartService.computeCartTotals();
  }

  incrementQuantity(cartItem: CartItem){
    this.cartService.addToCart(cartItem);
  }

  decrementQuantity(cartItem: CartItem){
    this.cartService.decrementQuantity(cartItem);
  }

  removeFromCart(cartItem: CartItem){
    this.cartService.removeFromCart(cartItem);
  }

  placeOrder(){
    this.cartService.placeOrder().subscribe(
      response => {
        this.cartService.removeAll();
        this.router.navigate(["/"]);
      }
    );
  }

}
