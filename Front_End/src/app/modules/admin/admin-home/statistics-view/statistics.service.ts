import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AppComponent } from '../../../../app.component';
import { map } from 'rxjs/operators';

@Injectable()
export class StatisticsService {

  private URL = AppComponent.environment.server;

  constructor(private httpClient: HttpClient) {

  }

  getData() {
    return this.httpClient.get(this.URL + '/statistics');
  }
}
