import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpEvent, HttpHandler, HttpRequest } from '@angular/common/http';
import { AuthenticationService } from './authentication.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TokenInterceptorService implements HttpInterceptor{

  private auth: AuthenticationService;

  constructor(auth: AuthenticationService) {
    this.auth = auth;
   }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    console.log("intercepotor called");
    if (this.auth.getToken()) {
      request = request.clone({
        setHeaders: {
          Authorization: `Basic ${this.auth.getToken()}`
        }
      });
    }

    return next.handle(request);
  }
}
