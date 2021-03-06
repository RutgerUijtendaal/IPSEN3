import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from '../../../core/auth/authentication.service';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.scss']
})
export class LogoutComponent implements OnInit {

  constructor(authenticationService: AuthenticationService) {
    authenticationService.logout();
  }

  ngOnInit() {
  }

}
