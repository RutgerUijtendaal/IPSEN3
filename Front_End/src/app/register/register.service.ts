import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AppComponent } from '../app.component';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(private httpClient: HttpClient) {
  }

  register(data) {
    const url = AppComponent.environment.server + '/couple/register';

    this.httpClient.post(url, data).subscribe((message: any) => {
      console.log('Hello World');
    });
  }
}
