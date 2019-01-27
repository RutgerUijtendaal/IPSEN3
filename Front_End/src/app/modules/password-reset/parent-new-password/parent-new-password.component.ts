import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';
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

  goodPassword1: boolean;
  goodPassword2: boolean;
  matchingPasswords: boolean;

  buttonClass: string;
  message: string;

  constructor(private httpClient: HttpClient, private route: ActivatedRoute, private router: Router) { }

  goodSave(message: string) {
    this.buttonClass = 'success';
    this.message = message;
    setTimeout(() => {
      this.router.navigateByUrl('/inloggen');
    }, 1500);
  }

  badSave(message: string) {
    this.buttonClass = 'danger';
    this.message = message;
    setTimeout(() => {
      this.buttonClass = 'primary';
    }, 1500);
  }

  startSaving() {
    if (this.password1 === undefined || this.password2 === undefined || this.password1.length === 0 || this.password2.length === 0) {
      this.badSave('Leeg');
    } else if (this.password1 !== this.password2) {
      this.badSave('Wachtwoorden komen niet overeen');
    } else if (this.password1.length <= 3) {
      this.badSave('Te kort wachtwoord');
    } else {
      this.savePassword();
    }
  }

  firstInputUpdated(event: any) {
    this.password1 = event.target.value;
    this.goodPassword1 = (this.password1.length > 3);
    this.matchingPasswords = (this.password1 === this.password2);
  }

  secondInputUpdated(event: any) {
    this.password2 = event.target.value;
    this.goodPassword2 = (this.password2.length > 3);
    this.matchingPasswords = (this.password1 === this.password2);
  }

  savePassword() {
    this.httpClient.put(this.URL + '/couple/password/' + this.token, this.password1).subscribe(retval => {
      if (retval === true) {
        this.goodSave('Wachtwoord succesvol veranderd.');
      } else {
        this.badSave('Ouderpaar niet herkent.');
      }
    }, error => {
      this.badSave('Wachtwoord kom niet worden opgeslagen. Verbinding mislukt.');
    });
  }

  ngOnInit() {
    this.token = this.route.snapshot.params['token'];
    this.buttonClass = 'primary';
    console.log(this.token);
  }

}

