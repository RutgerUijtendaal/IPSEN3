import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ValidatePhone} from '../validators/phone.validator';
import {ValidateEmail} from '../validators/email.validator';
import {Router} from '@angular/router';
import { RegisterService } from './register.service';
import { AppComponent } from '../app.component';

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

  constructor(private router: Router, private service: RegisterService) {
    console.log(AppComponent.environment);
  }

  ngOnInit() {
    this.initForm();
  }

  submitForm(): void {
    const collection: object = {
      firstName1: this.coupleForm.value.parentA.name,
      phoneNr1: this.coupleForm.value.parentA.phone,
      email1: this.coupleForm.value.parentA.email,

      firstName2: this.coupleForm.value.parentA.name,
      phoneNr2: this.coupleForm.value.parentA.phone,
      email2: this.coupleForm.value.parentA.email,

      isBorn: this.coupleForm.value.child.isBorn,
      date: (new Date(this.coupleForm.value.child.date)).getMilliseconds()
    };

    this.service.register(collection);
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

