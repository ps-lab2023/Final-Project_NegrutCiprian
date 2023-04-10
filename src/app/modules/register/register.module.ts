import { NgModule } from '@angular/core';
import { RegisterComponent } from './components/register/register.component';
import { SharedModule } from '../shared/shared.module';
import { RegisterRoutingModule } from './register-routing.module';
import { RegisterService } from './services/register.service';

@NgModule({
  declarations: [RegisterComponent],
  imports: [SharedModule, RegisterRoutingModule],
  providers: [RegisterService],
})
export class RegisterModule {}
