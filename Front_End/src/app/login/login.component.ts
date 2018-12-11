import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ValidateEmail} from '../validators/email.validator';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  adminForm: FormGroup;


  constructor() {
  }

  private initForm() {
    this.adminForm = new FormGroup({
      'email': new FormControl('', [Validators.required, ValidateEmail]),
      'password': new FormControl('', [Validators.required, Validators.minLength(8)])
    });
  }

  submitForm(): void {
    console.log(this.adminForm.value);
  }

  ngOnInit() {
    this.initForm();
  }

}
