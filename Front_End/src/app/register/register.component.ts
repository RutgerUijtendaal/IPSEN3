import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';

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

  constructor() {
  }

  ngOnInit() {
    this.initForm();
  }

  submitForm(): void {
    console.log(this.coupleForm.value)
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
    })
  }

}

