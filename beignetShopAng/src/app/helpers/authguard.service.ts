import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {AuthenticationService} from '../services/authentication.service';

@Injectable({
  providedIn: 'root'
})
export class AuthguardService implements CanActivate {
  constructor(
    private router: Router,
    private authenticationService: AuthenticationService
  ) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    if (this.authenticationService.isConnected() && !this.authenticationService.isexpired()) {
      // logged in so return true
      return true;
    }

    // not logged in so redirect to login page with the return url
    // this.router.navigate(['/connexion'], { queryParams: { returnUrl: state.url } });
    this.router.navigate(['/connexion']);
    return false;
  }
}
