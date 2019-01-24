import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AppComponent } from '../../../../app.component';

@Injectable({
  providedIn: 'root'
})
export class RegisterNewService {

  constructor(private httpClient: HttpClient) {
  }

  register(data) {
    const url = AppComponent.environment.server + '/couple/register';
    console.log('extension in register(data): ' + url);

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json'
      })
    };

    return this.httpClient.post(url, data, httpOptions);
  }
}
