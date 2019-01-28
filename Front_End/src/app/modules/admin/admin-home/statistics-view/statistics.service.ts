import { EventEmitter, Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AppComponent } from '../../../../app.component';

@Injectable()
export class StatisticsService {

  private URL = AppComponent.environment.server + '/statistics';

  data = new EventEmitter<any>();
  couples = [];

  constructor(private httpClient: HttpClient) {
    this.getData();

  }

  getData() {
    this.httpClient.get(this.URL).subscribe(data => this.data.emit(data));
  }

  update() {
    const allData = { 'couples': this.couples};
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
      })
    };
    this.httpClient.post(this.URL, JSON.stringify(allData), httpOptions).subscribe(data => this.data.emit(data));
  }
}
