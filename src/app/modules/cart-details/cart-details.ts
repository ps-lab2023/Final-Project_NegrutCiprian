import { Component } from '@angular/core';
import { CartItem } from 'src/app/models/cart-item';
import { CartService } from '../home/services/cart-service';
import { Router } from '@angular/router';
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-cart-details',
  templateUrl: './cart-details.html',
  styleUrls: ['./cart-details.scss']
})

export class CartDetailsComponent {

  cartItems: CartItem[] = [];
  totalPrice: number = 0;
  totalQuantity: number = 0;
  formPromo: FormGroup;

  constructor(private cartService: CartService, private router: Router,
              private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.formPromo = this.formBuilder.group({
      promo: new FormControl('', [])
    });
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

  makePromoOrder(value: String) {
    let x: number = +value.substring(5,7);
    this.cartService.promoDiscount = x;
    this.cartService.computeCartTotals();
    // let totalPrice: number = 0
    // this.cartService.totalPrice.pipe().subscribe(val => totalPrice = val);
    // this.cartService.totalPrice.next(totalPrice - (totalPrice * (x/100)));
    // console.log(this.cartService.totalPrice.pipe().subscribe(val => console.log(val)));
  }

  openPopupPromoCart(){
    let popup = document.getElementById("popup-promo-cart");
    popup!.classList.add("open-popup-promo-cart");
    this.formPromo.patchValue({
      promo: ""
    })
    console.log(this.formPromo.value);
  }

  closePopupPromoCart(){
    let popup = document.getElementById("popup-promo-cart");
    popup!.classList.remove("open-popup-promo-cart");
    this.makePromoOrder(this.formPromo.controls['promo'].value);
  }

}
