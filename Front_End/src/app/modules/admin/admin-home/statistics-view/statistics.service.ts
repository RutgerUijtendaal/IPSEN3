import { EventEmitter, Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AppComponent } from '../../../../app.component';

@Injectable()
export class StatisticsService {

  private URL = AppComponent.environment.server + '/statistics';

  data = new EventEmitter<any>();
  filter = new EventEmitter<any>();
  activeCharts = [];
  activeFilters = [];
  couples = [];
  dilemmas = [];

  constructor(private httpClient: HttpClient) {
    this.getData();

  }

  getData() {
    this.filter = new EventEmitter();
    this.httpClient.get(this.URL).subscribe(data => this.data.emit(data));
  }

  update() {
    this.filter.emit();
    const allData = { 'couples': this.couples, 'dilemmas' : this.dilemmas};
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
      })
    };
    this.httpClient.post(this.URL, JSON.stringify(allData), httpOptions).subscribe(data => this.data.emit(data));
  }
}
