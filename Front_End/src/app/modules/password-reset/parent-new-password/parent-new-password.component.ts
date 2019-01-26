import { Component, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ActivatedRoute} from '@angular/router';
import {AppComponent} from '../../../app.component';

@Component({
  selector: 'app-parent-new-password',
  templateUrl: './parent-new-password.component.html',
  styleUrls: ['./parent-new-password.component.scss']
})
export class ParentNewPasswordComponent implements OnInit {

  URL = AppComponent.environment.server;

  token: string;

  password1: string;
  password2: string;

  buttonClass: string;
  buttonText: string;

  constructor(private httpClient: HttpClient, private route: ActivatedRoute) { }

  resetButton() {
    this.buttonClass = 'primary';
    this.buttonText = 'Opslaan';
  }

  errorMessage(message: string) {
    this.buttonClass = 'danger';
    this.buttonText = message;
    setTimeout(() => {
      this.resetButton();
    }, 1500);
  }

  startSaving() {
    console.log('starting saving');
    if (this.password1 === undefined || this.password2 === undefined) {
      this.errorMessage('Leeg wachtwoord veld');
    } else if (this.password1 !== this.password2) {
      this.errorMessage('Wachtwoorden komen niet overeen');
    } else if (this.password1.length <= 3) {
      this.errorMessage('Te kort wachtwoord');
    } else {
      this.savePassword();
    }
  }

  firstInputUpdated(event: any) {
    this.password1 = event.target.value;
  }

  secondInputUpdated(event: any) {
    this.password2 = event.target.value;
  }

  savePassword() {
    console.log('actually saving');
    this.httpClient.put(this.URL + '/couple/password/' + this.token, this.password1).subscribe(retval => {
      console.log(retval);
    });
  }

  ngOnInit() {
    this.token = this.route.snapshot.params['token'];
    this.resetButton();
    console.log(this.token);
  }

}
