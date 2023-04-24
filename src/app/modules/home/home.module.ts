import { NgModule } from '@angular/core';
import { HomeComponent } from './components/home/home.component';
import { SharedModule } from '../shared/shared.module';
import { HomeRoutingModule } from './home-routing.module';
import {ViewOrderComponent} from "./components/view-order/view-order.component";
import {ContactComponent} from "./components/contact/contact.component";

@NgModule({
  declarations: [HomeComponent, ViewOrderComponent, ContactComponent],
  imports: [SharedModule, HomeRoutingModule],
})
export class HomeModule {}
