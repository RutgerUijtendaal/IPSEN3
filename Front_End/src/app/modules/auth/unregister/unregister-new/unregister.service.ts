import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import { AppComponent } from '../../../../app.component';

@Injectable({
  providedIn: 'root'
})
export class UnregisterService {

  constructor(private httpClient: HttpClient) {
  }

  unregister(token) {
    const url = AppComponent.environment.server + '/couple/unregister';
    console.log('extension in unregister(token): ' + url);

    let params = new HttpParams().set('token', token);

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json'
      }),
      params: params,
    };

    return this.httpClient.delete(url, httpOptions);
  }
}
