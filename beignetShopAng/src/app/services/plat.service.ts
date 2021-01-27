import { Injectable } from '@angular/core';
import {Observable, of} from 'rxjs';
import {catchError} from 'rxjs/operators';
import {HttpClient} from '@angular/common/http';
import {GlobalService} from './global.service';
import {Plat} from '../models/plat';

@Injectable({
  providedIn: 'root'
})
export class PlatService {
  constructor(private http: HttpClient, private global: GlobalService) { }


  getAll(): Observable<Plat[]> {
    return this.http.get<Plat[]>(this.global.urlBase + 'plats')
      .pipe(
        catchError(this.handleError<Plat[]>('getAll', []))
      );
  }

  getOne(id): Observable<Plat> {
    return this.http.get<Plat>(this.global.urlBase + 'plat/get/' + id)
      .pipe(
        catchError(this.handleError<Plat>('getOne'))
      );
  }


  add(data) {
    return this.http.post(this.global.urlBase + 'plat/add', data);
  }

  delete(id) {
    return this.http.delete(this.global.urlBase + 'plat/delete/' + id);
  }

  update(data) {
    return this.http.post(this.global.urlBase + 'plat/update', data);
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
