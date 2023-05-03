import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Product } from '../../../models/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private baseUrl = 'http://localhost:8080/perfume?page=0&size=100&sort=asc';

  constructor(private httpClient: HttpClient) {
  }

  filterProducts(categoryId: string, products: Product[]) {
    let filteredProducts: Product[] = [];
    if (categoryId == '') {
      return products;
    }
    if (categoryId == 'price-asc') {
      filteredProducts = products;
      filteredProducts.sort((a, b) => a.price - b.price);
    } else if (categoryId == 'price-desc') {
      filteredProducts = products;
      filteredProducts.sort((a, b) => b.price - a.price);
    } else {
      filteredProducts = products.filter(p => p.category == categoryId);
    }
    return filteredProducts;
  }

  getProductList(categoryId: string): Observable<Product[]> {
    const searchUrl = this.baseUrl;
    return this.httpClient.get<Map<String, any>>(searchUrl).pipe(
      map(response => {
        let mapResponse = new Map(Object.entries(response));
        return this.filterProducts(categoryId, mapResponse.get('content'));
      })
    );
  }

  addProduct(name: String, category: string, desc: string, price: number): Observable<Map<String, any>> {
    const addUrl = this.baseUrl + '/create';
    return this.httpClient.post<Map<String, any>>(
      addUrl,
      {
        name: name,
        category: category,
        description: desc,
        price: price,
        promo: "",
        outOfStock: false
      }
    ).pipe(
      map(response => {
        console.log("Add response:" + response);
        const mapResponse = new Map(Object.entries(response));
        return mapResponse;
      })
    );
  }

  deleteProduct(id: string): Observable<Map<String, any>> {
    console.log('DELETE');
    console.log(id);
    const deleteUrl = `http://localhost:8080/perfume/${id}`;
    return this.httpClient.delete<any>(deleteUrl).pipe(
      map(response => {
        console.log(response);
        return response;
      })
    );
  }

  updateProduct(id: String, product: Product):
    Observable<Map<String, any>> {
    console.log('UPDATE');
    console.log(id);
    const updateUrl = `http://localhost:8080/perfume/${id}`;
    return this.httpClient.put<Map<String, any>>(
      updateUrl,
      {
        name: product.name,
        category: product.category,
        description: product.description,
        price: product.price,
        promo: product.promo,
        outOfStock: product.outOfStock
      }
    )
  }
}
