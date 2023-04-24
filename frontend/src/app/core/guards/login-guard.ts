import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';


@Injectable()
export class LoginGuard implements CanActivate {

  constructor( public authService: AuthService, public router: Router){

  }

  canActivate() {
    console.log(this.authService.isLogged());
    if ( this.authService.isLogged()) {
      return true;
    }else {
      this.router.navigate(['/login']);
      return false;
    }
  }

}
