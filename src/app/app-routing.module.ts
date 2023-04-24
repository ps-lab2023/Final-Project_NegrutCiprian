import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './modules/core/guards/auth.guard';
import { LayoutComponent } from './modules/core/components/layout/layout.component';
import { CartDetailsComponent } from './modules/cart-details/cart-details';
import { AddProductComponent } from './modules/add-product/add-product';
import {ViewOrderComponent} from "./modules/home/components/view-order/view-order.component";
import {ContactComponent} from "./modules/home/components/contact/contact.component";

const routes: Routes = [
  {path: "cart-details", component: CartDetailsComponent},
  {path: "add-product", component: AddProductComponent},
  {path: "app-view-order", component: ViewOrderComponent},
  {path: "app-contact", component: ContactComponent},
  {
    path: '',
    component: LayoutComponent,
    canActivate: [AuthGuard],
    children: [
      {
        path: '',
        loadChildren: () => import('./modules/home/home.module').then((m) => m.HomeModule),
      },
    ],
  },
  {
    path: "category/:type",
    component: LayoutComponent,
    canActivate: [AuthGuard],
    children: [
      {
        path: '',
        loadChildren: () => import('./modules/home/home.module').then((m) => m.HomeModule),
      },
    ],
  },
  {
    path: 'login',
    loadChildren: () => import('./modules/login/login.module').then((m) => m.LoginModule),
  },
  {
    path: 'register',
    loadChildren: () => import('./modules/register/register.module').then((m) => m.RegisterModule),
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
