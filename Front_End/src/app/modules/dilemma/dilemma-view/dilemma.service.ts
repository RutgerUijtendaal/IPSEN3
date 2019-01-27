import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {AppComponent} from '../../../app.component';
import {Observable} from 'rxjs';
import {DilemmaModel} from '../../../shared/models/dilemma.model';

@Injectable({
  providedIn: 'root'
})
export class DilemmaService {
  private readonly url: string;

  constructor(private httpClient: HttpClient) {
    this.url = AppComponent.environment.server;
  }

  public getDilemmaAnswerDatabag(token: string): Observable<Object> {
    return this.httpClient.get(this.url + '/dilemma/answer/' + token);
  }

  public submitDilemmaAnswer(token: string, answerId: number) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/x-www-form-urlencoded'
      })
    };

    const formData: URLSearchParams = new URLSearchParams();
    formData.append('answerId', answerId.toString());

    return this.httpClient.post(this.url + '/dilemma/answer/' + token, formData.toString(), httpOptions);
  }

  public submitDilemmaFeedback(rating, dilemma: DilemmaModel) {
    let params = new HttpParams();
    params = params.set('dilemmaId', String(dilemma.id));
    params = params.set('rating', String(rating));

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json'
      }),
      params: params,
    };

    return this.httpClient.post(this.url + "/rating", {}, httpOptions);
  }
}

