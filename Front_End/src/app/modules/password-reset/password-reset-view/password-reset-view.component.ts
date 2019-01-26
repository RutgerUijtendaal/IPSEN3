import { Component, OnInit } from '@angular/core';
import {AppComponent} from '../../../app.component';
import {HttpClient} from '@angular/common/http';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-password-reset-view',
  templateUrl: './password-reset-view.component.html',
  styleUrls: ['./password-reset-view.component.scss']
})
export class PasswordResetViewComponent implements OnInit {

  URL = AppComponent.environment.server;

  email: string;

  buttonClass: string;
  buttonText: string;

  constructor(private httpClient: HttpClient, private route: ActivatedRoute) { }

  resetButton() {
    this.buttonClass = 'primary';
    this.buttonText = 'Resetten';
  }

  errorMessage(message: string) {
    this.buttonClass = 'danger';
    this.buttonText = message;
    setTimeout(() => {
      this.resetButton();
    }, 1500);
  }

  startSaving() {
    if (this.email === undefined || this.email.length === 0) {
      this.errorMessage('Leeg email veld');
    } else {
      this.resetPassword();
    }
  }

  emailUpdated(event: any) {
    this.email = event.target.value;
  }

  resetPassword() {
    this.httpClient.post(this.URL + '/password/request-reset/' + this.email, null).subscribe(retval => {
    });
  }

  ngOnInit() {
    this.resetButton();
  }

}
