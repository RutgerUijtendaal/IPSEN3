import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AppComponent } from '../app.component';

@Injectable({
  providedIn: 'root'
})
export class UnregisterService {

  constructor(private httpClient: HttpClient) {
  }

  unregister(token) {
    const url = AppComponent.environment.server + '/couple/unregister';
    console.log('url in unregister(token): ' + url);

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json'
      })
    };

    return this.httpClient.post(url, token, httpOptions);
  }
}
