import {AccountModel} from '../models/account.model';
import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {AppComponent} from '../app.component';


@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  accountModel: AccountModel;

  constructor(private httpClient: HttpClient) {
    this.accountModel = new AccountModel();
  }

  login(email: string, password: string) {
    const url = AppComponent.environment.server + '/login';
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
      })
    };
    const logindata = {'email' : email, 'password' : password};
    console.log(logindata);
    this.httpClient.post(url, logindata, httpOptions).subscribe(data => {
      if (data != null) {
        const loginData = data as AccountModel;
        this.accountModel.type = loginData.type;
        this.accountModel.name = loginData.name;
        this.accountModel.email = loginData.email;
        this.accountModel.password = loginData.password;
      }
      console.log(this.accountModel.type);
    });
  }
}
