import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { AuthInterceptor } from './interceptors/auth.interceptor';
import { TokenService } from './services/token.service';
import { AuthGuard } from './guards/auth.guard';
import { LayoutComponent } from './components/layout/layout.component';
import { RouterOutlet } from '@angular/router';

@NgModule({
  declarations: [LayoutComponent],
  imports: [BrowserModule, BrowserAnimationsModule, HttpClientModule, RouterOutlet],
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }, TokenService, AuthGuard],
  exports: [
    LayoutComponent
  ]
})
export class CoreModule {}
