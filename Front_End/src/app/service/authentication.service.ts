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
    console.log(localStorage.getItem('login'));
    const loginData: string = localStorage.getItem('login');
    if (loginData != null) {
      this.accountModel.setData(JSON.parse(loginData));
    }
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
        this.accountModel.setData(data);
        localStorage.setItem('login', JSON.stringify(this.accountModel));
      }
    });
  }

  logout() {
    localStorage.clear();
    this.accountModel.type = null;
  }
}
