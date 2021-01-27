import { Injectable } from '@angular/core';
import {Observable, of} from 'rxjs';
import {catchError} from 'rxjs/operators';
import {HttpClient} from '@angular/common/http';
import {GlobalService} from './global.service';
import {Produit} from '../models/produit';
import {Utilisateur} from '../models/utilisateur';

@Injectable({
  providedIn: 'root'
})
export class UtilisateurService {
  constructor(private http: HttpClient, private global: GlobalService) { }


  getAll(): Observable<Utilisateur[]> {
    return this.http.get<Utilisateur[]>(this.global.urlBase + 'utilisateurs')
      .pipe(
        catchError(this.handleError<Utilisateur[]>('getAll', []))
      );
  }

  getOne(id): Observable<Utilisateur> {
    return this.http.get<Utilisateur>(this.global.urlBase + 'utilisateur/get/' + id)
      .pipe(
        catchError(this.handleError<Utilisateur>('getOne'))
      );
  }

  add(data) {
    return this.http.post(this.global.urlBase + 'utilisateur/add', data);
  }

  delete(id) {
    return this.http.delete(this.global.urlBase + 'utilisateur/delete/' + id);
  }

  update(data) {
    return this.http.put(this.global.urlBase + 'utilisateur/update', data);
  }

  updateStatus(data) {
    return this.http.put(this.global.urlBase + 'utilisateur/updateStatus', data);
  }

  /**
   * Handle Http operation that failed.
   * Let the app continue.
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}
