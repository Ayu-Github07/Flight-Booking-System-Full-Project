import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  Router,
  RouterStateSnapshot,
  UrlTree,
} from '@angular/router';
import { Observable } from 'rxjs';
import Swal from 'sweetalert2';
import { LoginService } from './services/login/login.service';

@Injectable({
  providedIn: 'root',
})
export class HasRoleGuardGuard implements CanActivate {
  constructor(private loginService: LoginService, private router: Router) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ):
    | Observable<boolean | UrlTree>
    | Promise<boolean | UrlTree>
    | boolean
    | UrlTree {
    let role = localStorage.getItem('role');

    if (role === 'ADMIN') {
      return true;
    }

    Swal.fire({
      icon: 'error',
      title: 'Forbidden',
      text: `You don't have admin rights`,
    });
    this.router.navigate(['']);
    return false;
  }
}
