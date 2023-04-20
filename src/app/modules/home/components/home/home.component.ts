import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductService } from '../../services/products-service';
import { CartService } from '../../services/cart-service';
import { Product } from '../../../../models/product';
import { CartItem } from '../../../../models/cart-item';
import { TokenService } from '../../../core/services/token.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  products: Product[] = [];
  currentCategoryId: string = "";
  searchMode: boolean = false;

  constructor(private productService: ProductService,
              private cartService: CartService,
              private route: ActivatedRoute,
              private tokenService: TokenService,
              private router: Router) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(() => {
      this.listProducts();
    });
  }

  listProducts(){
    this.handleListProducts();

    // this.searchMode = this.route.snapshot.paramMap.has("keyword");
    // if (this.searchMode){
    //   // this.handleSearchProducts();
    // }else{
    //   this.handleListProducts();
    // }
  }

  // handleSearchProducts(){
  //   const theKeyword = this.route.snapshot.paramMap.get("keyword")!;
  //   this.productService.searchProducts(theKeyword).subscribe(
  //     data => {
  //       this.products = data;
  //     }
  //   );
  // }

  handleListProducts(){
    console.log(this.route.snapshot.paramMap);
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
      }
    );
  }

  addToCart(product: Product) {
    const cartItem = new CartItem(product);
    this.cartService.addToCart(cartItem);
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

}
