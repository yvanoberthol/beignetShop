import { Injectable } from '@angular/core';
import {Produit} from '../models/produit';
import {Observable, of} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {GlobalService} from './global.service';
import {catchError} from 'rxjs/operators';
import {Commande} from '../models/commande';

@Injectable({
  providedIn: 'root'
})
export class CommandeService {
  constructor(private http: HttpClient, private global: GlobalService) { }


  getAll(utilisateurId) {
    return this.http.get(this.global.urlBase + 'commande/utilisateur/' + utilisateurId)
      .pipe(
        catchError(this.handleError('getAll', []))
      );
  }

  getOne(id) {
    return this.http.get(this.global.urlBase + 'commande/get/' + id)
      .pipe(
        catchError(this.handleError('getOne'))
      );
  }

  add(data) {
    return this.http.post(this.global.urlBase + 'commande/add', data);
  }

  delete(id) {
    return this.http.delete(this.global.urlBase + 'commande/delete/' + id);
  }

  update(data) {
    return this.http.post(this.global.urlBase + 'commande/update', data);
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
