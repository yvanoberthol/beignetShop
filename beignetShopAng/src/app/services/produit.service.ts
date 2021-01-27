import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {GlobalService} from './global.service';
import {Produit} from '../models/produit';
import {Observable, of} from 'rxjs';
import {catchError} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ProduitService {

  constructor(private http: HttpClient, private global: GlobalService) { }


  getAll(): Observable<Produit[]> {
    return this.http.get<Produit[]>(this.global.urlBase + 'produits')
      .pipe(
        catchError(this.handleError<Produit[]>('getAll', []))
      );
  }

  getOne(id): Observable<Produit> {
    return this.http.get<Produit>(this.global.urlBase + 'produit/get/' + id)
      .pipe(
        catchError(this.handleError<Produit>('getOne'))
      );
  }

  add(data) {
    return this.http.post(this.global.urlBase + 'produit/add', data);
  }

  delete(id) {
    return this.http.delete(this.global.urlBase + 'produit/delete/' + id);
  }

  update(data) {
    return this.http.post(this.global.urlBase + 'produit/update', data);
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
