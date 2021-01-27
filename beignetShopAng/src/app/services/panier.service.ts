import { Injectable } from '@angular/core';
import {Observable, of} from 'rxjs';
import {Plat} from '../models/plat';
import {catchError} from 'rxjs/operators';
import {HttpClient} from '@angular/common/http';
import {GlobalService} from './global.service';
import {Panier} from '../models/panier';

@Injectable({
  providedIn: 'root'
})
export class PanierService {
  constructor(private http: HttpClient, private global: GlobalService) { }


  getAll(utilisateurId): Observable<Panier[]> {
    return this.http.get<Panier[]>(this.global.urlBase + 'paniers/' + utilisateurId)
      .pipe(
        catchError(this.handleError<Panier[]>('getAll', []))
      );
  }

  getOne(id): Observable<Panier> {
    return this.http.get<Panier>(this.global.urlBase + 'panier/get/' + id)
      .pipe(
        catchError(this.handleError<Panier>('getOne'))
      );
  }

  getPanierUtilisateur(utilisateurId) {
    return this.http.get(this.global.urlBase + 'panier/utilisateur/' + utilisateurId)
      .pipe(
        catchError(this.handleError('getPanierUtilisateur'))
      );
  }


  add(data) {
    return this.http.post(this.global.urlBase + 'panier/add', data);
  }

  delete(id) {
    return this.http.delete(this.global.urlBase + 'panier/delete/' + id);
  }

  update(data) {
    return this.http.post(this.global.urlBase + 'panier/update', data);
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
