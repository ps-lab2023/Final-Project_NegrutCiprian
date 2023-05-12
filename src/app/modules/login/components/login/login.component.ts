import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {LoginService} from '../../services/login.service';
import {TokenService} from '../../../core/services/token.service';
import {WebSocketAPI} from "../greeting-message/WebSocketAPI";
import {GreetingMessageComponent} from "../greeting-message/greeting-message.component";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {
  loginForm!: FormGroup;
  username!: AbstractControl;
  password!: AbstractControl;
  webSocketAPI: WebSocketAPI;

  constructor(private formBuilder: FormBuilder, private router: Router, public loginService: LoginService,
              private tokenService: TokenService) {
  }

  ngOnInit(): void {
    if (this.tokenService.getAccessToken()) {
      this.router.navigate(['/']);
    }
    this.reactiveForm();
    this.username = this.loginForm.get('username')!;
    this.password = this.loginForm.get('password')!;
    this.webSocketAPI = new WebSocketAPI(new GreetingMessageComponent());
  }

  onRegister() {
    this.router.navigate(['/register']);
  }

  reactiveForm() {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
    });
  }

  onLogin() {
    this.loginService.login(this.loginForm.get('username')?.value, this.loginForm.get('password')?.value);
    this.loginForm.reset();
    this.tokenService.connect(this.webSocketAPI);
  }
}
