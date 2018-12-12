import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import { RegisterService } from './register.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  formTitleParentA = "Uw Info";
  formTitleParentB = "Uw Partner";
  formTitleChild = "Jullie kind";
  coupleForm: FormGroup;

  constructor(private service: RegisterService) {
  }

  ngOnInit() {
    this.initForm();
  }

  submitForm(): void {
    console.log(this.coupleForm.value);
    const collection: object = {
      firstname1: this.coupleForm.value.parentA.name,
      phoneNr1: this.coupleForm.value.parentA.phone,
      email1: this.coupleForm.value.parentA.email,

      firstname2: this.coupleForm.value.parentA.name,
      phoneNr2: this.coupleForm.value.parentA.phone,
      email2: this.coupleForm.value.parentA.email,

      isBorn: this.coupleForm.value.child.isBorn,
      date: (new Date(this.coupleForm.value.child.date)).getMilliseconds()
    };

    this.service.register(collection);
  }

  private initForm() {
    this.coupleForm = new FormGroup({
      'parentA': new FormGroup({
        'name': new FormControl('', [Validators.required,  Validators.minLength(4)]),
        'email': new FormControl('', [Validators.required, Validators.email]),
        'phone': new FormControl('', Validators.required),
      }),
      'parentB': new FormGroup({
        'name': new FormControl('', [Validators.required,  Validators.minLength(4)]),
        'email': new FormControl('', [Validators.required, Validators.email]),
        'phone': new FormControl('', Validators.required),
      }),
      'child': new FormGroup({
        'date': new FormControl(''),
        'isBorn': new FormControl('')
      })
    });
  }

}

