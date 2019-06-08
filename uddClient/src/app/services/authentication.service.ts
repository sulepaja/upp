import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private token: string;
  private user: any | null;
  private redirectUrl: string | null;

  constructor() { }

  public setToken(token: string): void{
    this.token = token;
    localStorage.setItem('token', token);
  }

  public getToken(): string{
    return this.token || localStorage.getItem('token') || '';
  }

  public setUser(user: any): void {
    this.user = user;
    localStorage.setItem('user', JSON.stringify(user));
  }

  public getRedirectUrl(): string {
    return this.redirectUrl;
  }

  public setRedirectUrl(redirectUrl: string): void {
    this.redirectUrl = redirectUrl;
  }

  public getUser(): any | null {
    return (this.user || JSON.parse(localStorage.getItem('user')));
  }

  public getUserFromService(): any {
    return JSON.parse(localStorage.getItem('user'));
  }

  public logOutUser() {
    this.token = null;
    this.user = null;
    this.redirectUrl = null;
    localStorage.clear();
  }
}
