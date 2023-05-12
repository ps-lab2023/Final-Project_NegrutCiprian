import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import jwt_decode from 'jwt-decode';
import {WebSocketAPI} from "../../login/components/greeting-message/WebSocketAPI";

@Injectable()
export class TokenService {
  private TOKEN_KEY = 'accessToken';
  private REFRESH_TOKEN_KEY = 'refreshToken';
  private USERNAME_KEY = 'username';
  private ADMIN_KEY = 'adminKey';

  greeting: string;
  name: string;
  webSocketAPIStored: WebSocketAPI


  constructor(private router: Router) {}

  logout(): void {
    localStorage.clear();
    this.router.navigate(['/login']);
  }

  public saveAccessToken(token: string): void {
    localStorage.removeItem(this.TOKEN_KEY);
    localStorage.setItem(this.TOKEN_KEY, token);
  }

  public saveUsername(username: string): void{
    localStorage.removeItem(this.USERNAME_KEY);
    localStorage.setItem(this.USERNAME_KEY, username);
  }

  public getUsername(): string | null {
    return localStorage.getItem(this.USERNAME_KEY);
  }
  public saveAdminStatus(status: string): void{
    localStorage.removeItem(this.USERNAME_KEY);
    localStorage.setItem(this.ADMIN_KEY, status);
  }

  public getAdminStatus(): string | null {
    return localStorage.getItem(this.ADMIN_KEY);
  }

  public getAccessToken(): string | null {
    return localStorage.getItem(this.TOKEN_KEY);
  }

  public saveRefreshToken(token: string): void {
    localStorage.removeItem(this.REFRESH_TOKEN_KEY);
    localStorage.setItem(this.REFRESH_TOKEN_KEY, token);
  }

  public getRefreshToken(): string | null {
    return localStorage.getItem(this.REFRESH_TOKEN_KEY);
  }

  getDecodedAccessToken(token: string): any {
    try {
      return jwt_decode(token);
    } catch(Error) {
      return null;
    }
  }

  // Web Socket

  connect(webSocketAPI: WebSocketAPI){
    webSocketAPI._connect();
    this.webSocketAPIStored = webSocketAPI;
  }

  disconnect(){
    this.webSocketAPIStored._disconnect();
  }

  sendMessage(username: string){
    this.webSocketAPIStored._send(username);
  }
  // Web Socket

}
