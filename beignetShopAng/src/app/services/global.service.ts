import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class GlobalService {


  urlBase = 'http://localhost:8086/api/';
  urlConnexion = 'http://localhost:8086/';
  constructor() { }
}
