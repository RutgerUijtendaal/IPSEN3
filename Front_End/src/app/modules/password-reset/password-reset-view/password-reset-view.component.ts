import { Component, OnInit } from '@angular/core';
import {AppComponent} from '../../../app.component';
import {HttpClient} from '@angular/common/http';
import {ActivatedRoute, Router} from '@angular/router';
import {ValidateEmail} from '../../../shared/validators/email.validator';
import {FormControl, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-password-reset-view',
  templateUrl: './password-reset-view.component.html',
  styleUrls: ['./password-reset-view.component.scss']
})
export class PasswordResetViewComponent implements OnInit {

  URL = AppComponent.environment.server;

  email: string;

  buttonClass: string;
  message: string;
  goodEmail: boolean;

  constructor(private httpClient: HttpClient, private router: Router) { }

  resetButton() {
    this.buttonClass = 'primary';
    this.message = '';
  }

  startSaving() {
    if (this.email === undefined || this.email.length === 0) {
      this.badReset('Leeg email veld');
    } else {
      this.resetPassword();
    }
  }

  emailUpdated(event: any) {
    this.email = event.target.value;
    this.goodEmail = !ValidateEmail(new FormControl(this.email));
  }

  goodReset(message: string) {
    this.buttonClass = 'success';
    this.message = message;
    setTimeout(() => {
      this.router.navigateByUrl('/inloggen');
    }, 1500);
  }

  badReset(message: string) {
    this.buttonClass = 'danger';
    this.message = message;
    setTimeout(() => {
      this.buttonClass = 'primary';
    }, 1500);
  }

  resetPassword() {
    this.httpClient.post(this.URL + '/password/request-reset/' + this.email, null).subscribe(retval => {
      if (retval === true) {
        this.goodReset('Email gestuurd met reset link.');
      } else {
        this.badReset('Wachtwoord resetten niet gelukt. Bestaat de gebruiker wel?');
      }
    }, error => {
      this.badReset('Wachtwoord resetten niet gelukt. Verbinding verloren.');
    });
  }

  ngOnInit() {
    this.resetButton();
    this.goodEmail = false;
  }

}
