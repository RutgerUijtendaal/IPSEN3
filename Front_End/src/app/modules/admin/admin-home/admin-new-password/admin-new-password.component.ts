import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {AppComponent} from '../../../../app.component';

@Component({
  selector: 'app-admin-new-password',
  templateUrl: './admin-new-password.component.html',
  styleUrls: ['./admin-new-password.component.scss']
})
export class AdminNewPasswordComponent implements OnInit {

  URL = AppComponent.environment.server;

  token: string;

  password1: string;
  password2: string;

  buttonClass: string;
  buttonText: string;

  constructor(private httpClient: HttpClient, private route: ActivatedRoute) { }

  verifyInputs() {
    if (this.password1 === this.password2) {
      this.savePassword();
    } else {




    }
  }

  savePassword() {
    console.log('wtf');
    this.httpClient.put(this.URL + '/admin/password/' + this.token, this.password1).subscribe(retval => {
      console.log(retval);

    });
    return false;
  }

  ngOnInit() {
    this.token = this.route.snapshot.params['token'];
    this.buttonClass = 'primary';
    this.buttonText = 'Opslaan';
    console.log(this.token);

  }

}
