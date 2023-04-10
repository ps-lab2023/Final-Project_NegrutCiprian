import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { ProductService } from '../home/services/products-service';

@Component({
  selector: 'add-product',
  templateUrl: './add-product.html'
})
export class AddProductComponent implements OnInit {

  displayError: String = '';
  addSuccess: boolean = false;
  addProductFormGroup: FormGroup = new FormGroup({});

  constructor(private formBuilder: FormBuilder,
              private productService: ProductService,
              private router: Router
  ) {
  }

  ngOnInit(): void {
    this.addProductFormGroup = this.formBuilder.group({
      product: this.formBuilder.group({
        name: new FormControl('', []),
        category: new FormControl('', []),
        desc: new FormControl('', []),
        price: new FormControl('', [])
      })
    });
  }


  get name() {
    return this.addProductFormGroup.get('product.name')!;
  }

  get desc() {
    return this.addProductFormGroup.get('product.desc')!;
  }

  get category() {
    return this.addProductFormGroup.get('product.category')!;
  }

  get price() {
    return this.addProductFormGroup.get('product.price')!;
  }


  onSubmit() {
    console.log('Handling the submit button');
    this.addSuccess = false;
    if (this.addProductFormGroup.invalid) {
      this.addProductFormGroup.markAllAsTouched();
      this.addSuccess = false;
    } else {
      this.productService.addProduct(this.name.value, this.category.value,
        this.desc.value,
        this.price.value).subscribe(
        data => {
          console.log(data);
          this.addSuccess = true;
          this.displayError = '';
        }
      );
      console.log(this.addProductFormGroup.get('product')?.value);
    }
  }

}
