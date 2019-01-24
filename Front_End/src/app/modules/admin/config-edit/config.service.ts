import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {AppComponent} from '../../../app.component';
import {Observable} from 'rxjs';
import {ConfigModel} from '../../../shared/models/config.model';

@Injectable({
  providedIn: 'root'
})
export class ConfigService {
  private readonly url: string;

  constructor(private httpClient: HttpClient) {
    this.url = AppComponent.environment.server + '/admin/config/';
  }

  public getConfig(): Observable<Object> {
    return this.httpClient.get(this.url + 'all');
  }

  public updateConfig(weekDay: ConfigModel, dayTime: ConfigModel, reminder: ConfigModel): Observable<Object> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/x-www-form-urlencoded'
      })
    };

    const formData: URLSearchParams = new URLSearchParams();

    formData.append('weekday', weekDay.value);
    formData.append('daytime', dayTime.value);
    formData.append('reminder', reminder.value);

    return this.httpClient.post(this.url + 'update', formData.toString(), httpOptions);
  }
}
