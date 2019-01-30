import {Injectable} from '@angular/core';
import {Subject} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {AppComponent} from '../../app.component';

@Injectable()
export class PasswordResetHttpService {

  private URL = AppComponent.environment.server;

  success: Subject<string>;
  failure: Subject<string>;

  constructor(private httpClient: HttpClient) {
    this.success = new Subject();
    this.failure = new Subject();
    this.success.asObservable();
    this.failure.asObservable();
  }

  resetPassword(email: string) {
    const postUrl = this.URL + '/password/request-reset/' + email;
    this.httpClient.post(postUrl, null).subscribe(retval => {
      if (retval === true) {
        this.success.next('Email gestuurd met reset link.');
      } else {
        this.failure.next('Wachtwoord resetten niet gelukt. Bestaat de gebruiker wel?')
      }
    }, error => {
      this.failure.next('Wachtwoord resetten niet gelukt. Verbinding verloren.')
    });
  }

  saveCouplePassword(token: string, password: string) {
    const putUrl = this.URL + '/couple/password/' + token;
    this.httpClient.put(putUrl, password).subscribe(retval => {
      if (retval === true) {
        this.success.next('Wachtwoord succesvol veranderd.');
      } else {
        this.failure.next('Ouderpaar niet herkent.');
      }
    }, error => {
      this.failure.next('Wachtwoord niet opgeslagen. Verbinding verbroken.');
    });
  }

  saveAdminPassword(token: string, password: string) {
    const putUrl = this.URL + '/admin/password/' + token;
    this.httpClient.put(putUrl, password).subscribe(retval => {
      if (retval === true) {
        this.success.next('Wachtwoord succesvol veranderd.');
      } else {
        this.failure.next('Beheerder niet herkent.');
      }
    }, error => {
      this.failure.next('Wachtwoord niet opgeslagen. Verbinding verbroken.');
    });
  }
}
