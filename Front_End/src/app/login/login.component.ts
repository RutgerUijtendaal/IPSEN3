import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ValidateEmail} from '../validators/email.validator';
import {Router} from '@angular/router';
import {AuthenticationService} from '../service/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  adminForm: FormGroup;


  constructor(private router: Router, private authenticationService: AuthenticationService) {
  }

  private initForm() {
    // TODO HOGER ZETTEN
    this.adminForm = new FormGroup({
      'email': new FormControl('', [Validators.required, ValidateEmail]),
      'password': new FormControl('', [Validators.required, Validators.minLength(2)])
    });
  }

  onGoBack() {
    this.router.navigate(['../']);
  }

  submitForm(): void {
    console.log(this.adminForm.value);
    this.authenticationService.login(this.adminForm.value.email, this.adminForm.value.password);
  }

  ngOnInit() {
    this.initForm();
  }

}
