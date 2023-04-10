import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor, HttpErrorResponse, HttpContextToken } from '@angular/common/http';
import { catchError, Observable, switchMap, throwError } from 'rxjs';
import { TokenService } from '../services/token.service';
import { LoginService } from '../../login/services/login.service';
import { RegisterService } from '../../register/services/register.service';

export const BYPASS_LOG = new HttpContextToken(() => false);

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private tokenService: TokenService, private loginService: LoginService, private registerService: RegisterService) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    if (this.tokenService.getAccessToken() && !request.context.get(BYPASS_LOG)) {
      request = this.addTokenHeader(request, this.tokenService.getAccessToken()!);
    }
    return next.handle(request).pipe(
      catchError((error) => {
        if (error instanceof HttpErrorResponse && error.status === 401 && error.error === 'Expired refresh token\n') {
          this.tokenService.logout();
          return throwError(error);
        } else if (error instanceof HttpErrorResponse && error.status === 401 && this.tokenService.getAccessToken()) {
          return this.handleRefreshToken(request, next);
        } else if (error instanceof HttpErrorResponse && error.status === 401 && this.tokenService.getAccessToken()) {
          return this.handleRegisterRefreshToken(request, next);
        } else {
          return throwError(error);
        }
      })
    );
  }

  private addTokenHeader(request: HttpRequest<any>, token: string) {
    return request.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`,
      },
    });
  }

  private handleRefreshToken(request: HttpRequest<any>, next: HttpHandler) {
    return this.loginService.getRefreshToken().pipe(
      switchMap((token) => {
        return next.handle(this.addTokenHeader(request, token));
      })
    );
  }

  private handleRegisterRefreshToken(request: HttpRequest<any>, next: HttpHandler) {
    return this.registerService.getRefreshToken().pipe(
      switchMap((token) => {
        return next.handle(this.addTokenHeader(request, token));
      })
    );
  }

}
