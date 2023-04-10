import { Injectable } from '@angular/core';
import { TokenService } from '../../core/services/token.service';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders, HttpParams, HttpContext } from '@angular/common/http';
import { environment } from '../../../../environments/environment';
import { tap, throwError } from 'rxjs';
import { BYPASS_LOG } from '../../core/interceptors/auth.interceptor';

@Injectable({
  providedIn: 'root',
})
export class RegisterService {
  constructor(private http: HttpClient, private tokenService: TokenService, private router: Router) {}

  invalidCredentials = false;

  register(username: string, password: string, confirmPassword: string) {
    const body = new HttpParams().set('username', username).set('password', password).set('confirmPassword', confirmPassword);

    const headers = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded');
    return this.http.post(`${environment.apiUrl}/user/register`, body, { headers, responseType: 'json' }).subscribe({
      next: (response: any) => {
        this.tokenService.saveAccessToken(response['accessToken']);
        this.tokenService.saveRefreshToken(response['refreshToken']);
        this.router.navigate(['/']);
        this.invalidCredentials = false;
        this.tokenService.saveUsername(username);

      },
      error: (err) => {
        this.invalidCredentials = true;
        throwError(err);
      },
    });
  }

  getRefreshToken() {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');

    return this.http.post(`${environment.apiUrl}/user/refresh`, this.tokenService.getRefreshToken(), { headers, context: new HttpContext().set(BYPASS_LOG, true), responseType: 'json' }).pipe(
      tap((token: any) => {
        this.tokenService.saveAccessToken(token['accessToken']);
        this.tokenService.saveRefreshToken(token['refreshToken']);
      })
    );
  }
}
