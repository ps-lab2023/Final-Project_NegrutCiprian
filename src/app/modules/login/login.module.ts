import { NgModule } from '@angular/core';
import { LoginComponent } from './components/login/login.component';
import { SharedModule } from '../shared/shared.module';
import { LoginRoutingModule } from './login-routing.module';
import { LoginService } from './services/login.service';

@NgModule({
  declarations: [LoginComponent],
  imports: [SharedModule, LoginRoutingModule],
  providers: [LoginService],
})
export class LoginModule {}
