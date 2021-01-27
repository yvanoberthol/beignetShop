import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {GlobalService} from './global.service';
import {JwtHelperService} from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private currentUser: any;
  private currentUserInfo = {
    id: 0,
    name: '',
    roles: ''
  };
  public tokenTitle = 'userToken';
  public jwtToken: any;
  private readonly url;
  private role: any;

  constructor(private http: HttpClient, private globals: GlobalService) {
    this.url = globals.urlConnexion;
  }


  public login(form) {
    return this.http.post<any>(`${this.url}login`, form , { observe: 'response' });
  }

  public getUser() {
    this.loadToken();
    const jwtHelper = new JwtHelperService();
    const tokenDecoded = jwtHelper.decodeToken(this.jwtToken);
    this.currentUserInfo.id = tokenDecoded.id;
    this.currentUserInfo.name = tokenDecoded.username;
    this.currentUserInfo.roles = tokenDecoded.roles[0];
    return this.currentUserInfo;
  }

  public logout() {
    // remove user from local storage to log user out
    localStorage.removeItem(this.tokenTitle);
    this.currentUserInfo = {
      id: 0,
      name: '',
      roles: ''
    };
  }

  public saveToken(jwtToken) {
    this.jwtToken = jwtToken;
    localStorage.setItem(this.tokenTitle, jwtToken);
    const jwtHelper = new JwtHelperService();
    const tokenDecoded = jwtHelper.decodeToken(this.jwtToken);
    this.role = tokenDecoded.roles[0];
    this.currentUser = tokenDecoded.username;
  }

  public loadToken() {
    this.jwtToken = localStorage.getItem(this.tokenTitle);
    return this.jwtToken;
  }

  public isConnected() {
    const jwtHelper = new JwtHelperService();
    this.jwtToken = this.loadToken();
    if (this.jwtToken != null) {
      this.role = jwtHelper.decodeToken(this.jwtToken ).roles[0];
      if (this.role != null) {
        return true;
      }
    }
    return false;
  }

  public isAdmin() {
    const jwtHelper = new JwtHelperService();
    this.jwtToken = this.loadToken();
    if (this.jwtToken != null) {
      this.role = jwtHelper.decodeToken(this.jwtToken).roles[0].toLowerCase();
      if (this.role === 'admin') {
        return true;
      }
    }
    return false;
  }

  public isexpired() {
    const jwtHelper = new JwtHelperService();
    return jwtHelper.isTokenExpired(this.jwtToken );
  }
}
