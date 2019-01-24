import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {AppComponent} from '../../../app.component';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DilemmaService {
  private readonly url: string;

  constructor(private httpClient: HttpClient) {
    this.url = AppComponent.environment.server + '/dilemma/answer/';
  }

  public getDilemmaAnswerDatabag(token: string): Observable<Object> {
    return this.httpClient.get(this.url + token);
  }

  public submitDilemmaAnswer(token: string, answerId: number) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/x-www-form-urlencoded'
      })
    };

    const formData: URLSearchParams = new URLSearchParams();
    formData.append('answerId', answerId.toString());

    return this.httpClient.post(this.url + token, formData.toString(), httpOptions);
  }
}
