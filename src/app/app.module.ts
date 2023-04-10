import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { CoreModule } from "./modules/core/core.module";
import { SharedModule } from "./modules/shared/shared.module";
import { AppRoutingModule } from "./app-routing.module";
import { CartDetailsComponent } from './modules/cart-details/cart-details';
import { AddProductComponent } from './modules/add-product/add-product';

@NgModule({
  declarations: [
    AppComponent,
    AddProductComponent,
    CartDetailsComponent,
  ],
  imports: [
    CoreModule,
    SharedModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
