import {AccountModel} from '../../shared/models/account.model';
import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {AppComponent} from '../../app.component';
import {Router} from '@angular/router';


@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  accountModel: AccountModel;

  constructor(private httpClient: HttpClient, private router: Router) {
    this.accountModel = new AccountModel();
    const loginData: string = sessionStorage.getItem('login');
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

    return this.httpClient.post(url, logindata, httpOptions);
  }

  setLogin(data: any) {
    this.accountModel.setData(data);
    sessionStorage.setItem('login', JSON.stringify(this.accountModel));
  }

  logout() {
    sessionStorage.clear();
    this.accountModel.type = null;
  }
}
