import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductService } from '../../services/products-service';
import { CartService } from '../../services/cart-service';
import { Product } from '../../../../models/product';
import { CartItem } from '../../../../models/cart-item';
import { TokenService } from '../../../core/services/token.service';
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {FavoriteService} from "../../services/favorite.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  products: Product[] = [];
  currentCategoryId: string = "";
  form: FormGroup;

  constructor(private productService: ProductService,
              private cartService: CartService,
              private route: ActivatedRoute,
              private tokenService: TokenService,
              private router: Router,
              private formBuilder: FormBuilder,
              private favoriteService: FavoriteService) { }

  ngOnInit(): void {
    this.listProducts();
    this.initForm();
  }

  initForm(): void {
    this.form = this.formBuilder.group({
        name: new FormControl('', []),
        category: new FormControl('', []),
        description: new FormControl('', []),
        price: new FormControl('', []),
        externalId: new FormControl('', []),
        identifier: new FormControl('', []),
        promo: new FormControl('', []),
        outOfStock: new FormControl('', [])
    });
  }

  listProducts(){
    this.handleListProducts();
  }

  handleListProducts(){
    console.log(this.products);
    const hasCategoryId: boolean = this.route.snapshot.paramMap.has("type");

    if (hasCategoryId) {
      // get the "id" param string. convert string to a number using the "+" symbol
      this.currentCategoryId = this.route.snapshot.paramMap.get("type")!;
    } else {
      // not category id available ... default to category id 1
      this.currentCategoryId = "";
    }


    this.productService.getProductList(this.currentCategoryId).subscribe(
      data => {
        this.products = data;
        this.products.forEach(p => {
          if(localStorage.getItem(p.identifier) === "true")
            p.outOfStock = true;
        })
      }
    );
  }

  addToCart(product: Product) {
    if(!product.outOfStock) {
      const cartItem = new CartItem(product);
      this.cartService.addToCart(cartItem);
    }
  }

  addToFavorite(product: Product){
    console.log(product);
    const favItem = new CartItem(product);
    this.favoriteService.addToFavorite(favItem);
  }

  isAdmin(){
    let token = this.tokenService.getDecodedAccessToken(this.tokenService.getAccessToken()!);
    let responseMap = new Map(Object.entries(token));
    let roles = responseMap.get("roles") as string;
    return roles.indexOf("ROLE_ADMIN") != -1;
  }

  delete(id: string) {
    this.productService.deleteProduct(id).subscribe(
      data => {
        console.log(data);
        this.handleListProducts();
      }
    );
  }

  update(id: String, product: Product){
    console.log(product);
    this.productService.updateProduct(id, product).subscribe(
      () => this.handleListProducts()
    );
  }

  openPopup(product: Product){
    let popup = document.getElementById("popup");
    popup!.classList.add("open-popup");
    this.form.patchValue({
      name: product.name,
      category: product.category,
      description: product.description,
      price: product.price,
      externalId: product.externalId,
      identifier: product.identifier,
      promo: ""
    })
    console.log(this.form.value);
  }

  closePopup(){
    let popup = document.getElementById("popup");
    popup!.classList.remove("open-popup");
    let id = this.form.get("externalId").value;
    console.log(this.form.value);
    this.update(id, this.form.value);
  }

  openPopupPromo(product: Product){
    let popup = document.getElementById("popup-promo");
    popup!.classList.add("open-popup-promo");
    this.form.patchValue({
      name: product.name,
      category: product.category,
      description: product.description,
      price: product.price,
      externalId: product.externalId,
      identifier: product.identifier,
      promo: "%"
    })
    console.log(this.form.value);
  }

  closePopupPromo(){
    let popup = document.getElementById("popup-promo");
    popup!.classList.remove("open-popup-promo");
    let id = this.form.get("externalId").value;
    console.log(this.form.value);
    this.update(id, this.form.value);
  }

  setOutOfStock(product: Product){

    this.form.patchValue({
      name: product.name,
      category: product.category,
      description: product.description,
      price: product.price,
      externalId: product.externalId,
      identifier: product.identifier,
      promo: product.promo,
      outOfStock: !(product.outOfStock == true)
    })

    let id = this.form.get("externalId").value;
    console.log(this.form.value);
    this.update(id, this.form.value);
  }
}
