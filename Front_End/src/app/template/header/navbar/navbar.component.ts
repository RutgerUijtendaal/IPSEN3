import { Component, OnInit } from '@angular/core';
import {AccountModel} from '../../../models/account.model';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {
  accountModel: AccountModel;

  constructor() { }

  ngOnInit() {
    this.accountModel = new AccountModel('admin', 'Naam');
  }

}
