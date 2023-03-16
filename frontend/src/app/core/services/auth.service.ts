import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { User } from '../models/user';
import { Login } from '../models/login';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  user: User;
  token: string;

  constructor(
    private _http: HttpClient,
    private _router: Router) {
  }

  login(login: Login) {
    let url = `${environment.urlBackendSpringBoot}/api/auth/signin`;
    return this._http.post(url, login);
  }

  logout() {
    this.token = '';
    this.token = '';
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    this._router.navigate(['login']);
  }

  saveStorage( id: string, token: string, user: User ) {
    if ( id != null ) {
      localStorage.setItem('id', id );
    }
    if ( token != null ) {
      localStorage.setItem('token', token );
      this.token = token;
    }
    if ( user != null ) {
      localStorage.setItem('user', JSON.stringify(user) );
      this.user = user;
    }

    console.log("TCL: AuthService -> saveStorage ->  this.user",  this.user)

  }

  isLogged() {
    const token =  localStorage.getItem('token');
    return token != null && token.length > 8 ? true : false;
  }

  loadStorage() {
    if (localStorage.getItem("token")) {
      this.token = (localStorage.getItem("token")!);
      this.user = JSON.parse(localStorage.getItem('user')!);
      console.log("TCL: AuthService -> loadStorage ->  this.user",  this.user)
      return;
    } else {
      this.token = '';
      this.token = '';
    }
  }

  getUserLocalStorage() {
    if (localStorage.getItem("token")) {
      return JSON.parse(localStorage.getItem('user')!);
    }
    return null;
  }

  setLocalStorageLogin(data: any) {
    const response: User = data['user'];
    this.saveStorage( data['user']._id, data['token'], response );
    window.location.href = '#/dashboard';
  }

}
