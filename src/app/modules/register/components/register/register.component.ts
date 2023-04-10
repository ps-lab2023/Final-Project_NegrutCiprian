import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { RegisterService } from '../../services/register.service';
import { TokenService } from '../../../core/services/token.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  registerForm!: FormGroup;
  username!: AbstractControl;
  password!: AbstractControl;
  confirmPassword!: AbstractControl;

  constructor(private formBuilder: FormBuilder, private router: Router, public registerService: RegisterService, private tokenService: TokenService) {
  }

  ngOnInit(): void {
    if (this.tokenService.getAccessToken()) {
      this.router.navigate(['/']);
    }
    this.reactiveForm();
    this.username = this.registerForm.get('username')!;
    this.password = this.registerForm.get('password')!;
    this.confirmPassword = this.registerForm.get('confirmPassword')!;
  }

  reactiveForm() {
    this.registerForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
      confirmPassword: ['', Validators.required],
    });
  }

  onRegister() {
    this.registerService.register(this.registerForm.get('username')?.value, this.registerForm.get('password')?.value,
      this.registerForm.get('confirmPassword')?.value);
    this.registerForm.reset();
  }
}
