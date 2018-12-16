import {AccountModel} from '../models/account.model';
import {Injectable} from '@angular/core';


@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  accountModel: AccountModel

  constructor() {
    this.accountModel = new AccountModel('admin', 'Naam');
  }
}
