import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AppComponent } from '../app.component';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(private httpClient: HttpClient) {
  }

  register(data) {
    const url = AppComponent.environment.server + '/couple/register';

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json'
      })
    };

    this.httpClient.post(url, data, httpOptions).subscribe((message: any) => {
      console.log('Hello World');
    });
  }
}
