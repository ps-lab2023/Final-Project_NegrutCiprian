import { Product } from "./product";

export class CartItem {
  public product: Product;
  public quantity: number;

  constructor(product: Product){
    this.product = product;
    this.quantity = 1;
  }

  get id() {return this.product.identifier;}
  get name() {return this.product.name;}
  get price() {return this.product.price;}
}
