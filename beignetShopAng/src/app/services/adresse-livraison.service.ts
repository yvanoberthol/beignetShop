import { Injectable } from '@angular/core';
import {Observable, of} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {GlobalService} from './global.service';
import {Panier} from '../models/panier';
import {catchError} from 'rxjs/operators';
import {AdresseLivraison} from '../models/adresse-livraison';

@Injectable({
  providedIn: 'root'
})
export class AdresseLivraisonService {
  constructor(private http: HttpClient, private global: GlobalService) { }


  getAll(utilisateurId): Observable<AdresseLivraison[]> {
    return this.http.get<AdresseLivraison[]>(this.global.urlBase + 'adresseLivraison/utilisateur/' + utilisateurId)
      .pipe(
        catchError(this.handleError<AdresseLivraison[]>('getAll', []))
      );
  }

  getOne(id): Observable<Panier> {
    return this.http.get<Panier>(this.global.urlBase + 'AdresseLivraison/get/' + id)
      .pipe(
        catchError(this.handleError<Panier>('getOne'))
      );
  }

  add(data) {
    return this.http.post(this.global.urlBase + 'adresseLivraison/add', data);
  }

  delete(id) {
    return this.http.delete(this.global.urlBase + 'adresseLivraison/delete/' + id);
  }

  update(data) {
    return this.http.post(this.global.urlBase + 'adresseLivraison/update', data);
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
