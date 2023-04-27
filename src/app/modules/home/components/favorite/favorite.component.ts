import { Component } from '@angular/core';
import {CartItem} from "../../../../models/cart-item";
import {FavoriteService} from "../../services/favorite.service";

@Component({
  selector: 'app-favorite',
  templateUrl: './favorite.component.html',
  styleUrls: ['./favorite.component.scss']
})
export class FavoriteComponent {

  favProducts: CartItem[] = []
  totalPrice: number = 0;
  totalQuantity: number = 0;

  constructor(private favoriteService: FavoriteService) {}

  ngOnInit() {
    this.listFavoriteDetails();
  }

  listFavoriteDetails() {

    this.favProducts = this.favoriteService.favorites;

    this.favoriteService.totalPrice.subscribe(
      data => this.totalPrice = data
    );

    this.favoriteService.totalQuantity.subscribe(
      data => this.totalQuantity = data
    );

    this.favoriteService.computeCartTotals();
  }

  removeFromCart(cartItem: CartItem){
    this.favoriteService.removeFromCart(cartItem);
  }

  private getFavProducts() {
    return this.favProducts;
  }

}

