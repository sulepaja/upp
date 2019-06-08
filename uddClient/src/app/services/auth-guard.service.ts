import { Injectable } from '@angular/core';
import { AuthenticationService } from './authentication.service';
import { Router, ActivatedRouteSnapshot, RouterStateSnapshot, CanActivate} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate{

  constructor(private authService: AuthenticationService, private router: Router) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    let url: string = state.url;
    console.log("Usao u auth guard");
    return this.checkLogin(url);
  }

  checkLogin(url: string): boolean{
    
    this.authService.setRedirectUrl(url);
    this.router.navigate(['/login']);
    return false;
  }


}


