import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ValidatePhone} from '../validators/phone.validator';
import {ValidateEmail} from '../validators/email.validator';
import {Router} from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  formTitleParentA = 'Uw Info';
  formTitleParentB = 'Uw Partner';
  formTitleChild = 'Jullie kind';

  coupleForm: FormGroup;

  constructor(private router: Router) {
  }

  ngOnInit() {
    this.initForm();
  }

  submitForm(): void {
    console.log(this.coupleForm.value);
  }

  onGoBack() {
    this.router.navigate(['../']);
  }

  private initForm() { 'yyyy-MM-dd'
    this.coupleForm = new FormGroup({
      'parentA': new FormGroup({
        'name': new FormControl('', [Validators.required,  Validators.minLength(2)]),
        'email': new FormControl('', [Validators.required, ValidateEmail]),
        'phone': new FormControl('', [Validators.required, ValidatePhone]),
      }),
      'parentB': new FormGroup({
        'name': new FormControl('', [Validators.required,  Validators.minLength(2)]),
        'email': new FormControl('', [Validators.required, ValidateEmail]),
        'phone': new FormControl('', [Validators.required, ValidatePhone]),
      }),
      'child': new FormGroup({
        'date': new FormControl(null),
        'isBorn': new FormControl('')
      })
    });
  }

}

