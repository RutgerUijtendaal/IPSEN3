import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  formTitleParentA = 'Uw Info';
  formTitleParentB = 'Uw Partner';
  formTitleChild = 'Jullie kind';

  regexEmail = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
  regexPhone = "^\\+\\d{11}$";

  coupleForm: FormGroup;

  constructor() {
  }

  ngOnInit() {
    this.initForm();
  }

  submitForm(): void {
    console.log(this.coupleForm.value);
  }

  private initForm() { 'yyyy-MM-dd'
    this.coupleForm = new FormGroup({
      'parentA': new FormGroup({
        'name': new FormControl('', [Validators.required,  Validators.minLength(2)]),
        'email': new FormControl('', [Validators.required, Validators.pattern(this.regexEmail)]),
        'phone': new FormControl('', [Validators.required, Validators.pattern(this.regexPhone)]),
      }),
      'parentB': new FormGroup({
        'name': new FormControl('', [Validators.required,  Validators.minLength(4)]),
        'email': new FormControl('', [Validators.required, Validators.pattern(this.regexEmail)]),
        'phone': new FormControl('', [Validators.required, Validators.pattern(this.regexPhone)]),
      }),
      'child': new FormGroup({
        'date': new FormControl(null),
        'isBorn': new FormControl('')
      })
    });
  }

}

