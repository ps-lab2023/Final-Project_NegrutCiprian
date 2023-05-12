import { NgModule } from '@angular/core';
import { HomeComponent } from './components/home/home.component';
import { SharedModule } from '../shared/shared.module';
import { HomeRoutingModule } from './home-routing.module';
import { ViewOrderComponent } from "./components/view-order/view-order.component";
import { FormsModule } from "@angular/forms";

@NgModule({
  declarations: [HomeComponent, ViewOrderComponent],
  imports: [SharedModule, HomeRoutingModule, FormsModule],
})
export class HomeModule {}
