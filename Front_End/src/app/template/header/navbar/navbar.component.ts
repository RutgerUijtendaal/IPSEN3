import { Component, OnInit } from '@angular/core';
import {AccountModel} from '../../../models/account.model';
import {AuthenticationService} from '../../../service/authentication.service';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  URL = 'http://localhost:8080/admin/test';

  accountModel: AccountModel;

  constructor(private authenticationService: AuthenticationService, private httpClient: HttpClient) {
    this.accountModel = authenticationService.accountModel;
  }

  ngOnInit() {
    /*setTimeout(() => {
      this.httpClient.get(this.URL).subscribe(data => {
        console.log(data);
      });
    } , 1);*/
  }

}
