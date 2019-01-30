import { Component, OnInit } from '@angular/core';
import {AppComponent} from '../../../app.component';
import {HttpClient} from '@angular/common/http';
import {ActivatedRoute, Router} from '@angular/router';
import {ValidateEmail} from '../../../shared/validators/email.validator';
import {FormControl, FormGroup} from '@angular/forms';
import {PasswordResetHttpService} from '../password-reset-http.service';

@Component({
  selector: 'app-password-reset-view',
  templateUrl: './password-reset-view.component.html',
  styleUrls: ['./password-reset-view.component.scss']
})
export class PasswordResetViewComponent implements OnInit {

  email: string;

  buttonClass: string;
  message: string;
  goodEmail: boolean;

  disableButton: boolean;

  constructor(private router: Router,
              private httpService: PasswordResetHttpService) { }

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
      this.disableButton = false;
      this.router.navigateByUrl('/inloggen');
    }, 1500);
  }

  badReset(message: string) {
    this.buttonClass = 'danger';
    this.message = message;
    setTimeout(() => {
      this.buttonClass = 'primary';
      this.disableButton = false;
    }, 1500);
  }

  resetPassword() {
    this.disableButton = true;
    this.httpService.resetPassword(this.email);
  }

  ngOnInit() {
    this.resetButton();
    this.goodEmail = false;
    this.disableButton = false;
    this.httpService.failure.subscribe(message => this.badReset(message));
    this.httpService.success.subscribe(message => this.goodReset(message));
  }

}
