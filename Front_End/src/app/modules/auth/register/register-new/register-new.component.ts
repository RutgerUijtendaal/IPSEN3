import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ValidatePhone} from '../../../../shared/validators/phone.validator';
import {ValidateEmail} from '../../../../shared/validators/email.validator';
import {ActivatedRoute, Router, RouterLinkActive} from '@angular/router';
import { RegisterNewService } from './register-new.service';
import { AppComponent } from '../../../../app.component';
import {ValidateBirthdate} from '../../../../shared/validators/birthdate.validator';

@Component({
  selector: 'app-register-new',
  templateUrl: './register-new.component.html',
  styleUrls: ['./register-new.component.scss']
})
export class RegisterNewComponent implements OnInit {
  submitLoading = false;
  showError = false;
  errorMessage = '';
  formTitleParentA = 'Uw Info';
  formTitleParentB = 'Uw Partner';
  formTitleChild = 'Gezamelijke Informatie';

  coupleForm: FormGroup;

  constructor(private router: Router, private service: RegisterNewService, private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.initForm();
  }

  submitForm(): void {
    this.submitLoading = true;

    const collection: object = {
      firstName1: this.coupleForm.value.parentA.name,
      phoneNr1: this.coupleForm.value.parentA.phone,
      email1: this.coupleForm.value.parentA.email,

      firstName2: this.coupleForm.value.parentB.name,
      phoneNr2: this.coupleForm.value.parentB.phone,
      email2: this.coupleForm.value.parentB.email,

      isBorn: this.coupleForm.value.child.isBorn,
      date: (new Date(this.coupleForm.value.child.date)).getTime(),
      password: this.coupleForm.value.child.password
    };

    this.service.register(collection).subscribe(
      (message: any) => {
        this.submitLoading = false;
        this.router.navigate(['succes'], {relativeTo: this.route});
      },
      (error: any) => {
        this.submitLoading = false;
        this.errorMessage = 'Er is iets fout gegaan tijdens het Registreren';
        this.showError = true;
        this.scrollToTop();
      });
  }

  private initForm() {
    // yyyy-MM-dd

    this.coupleForm = new FormGroup({
      'parentA': new FormGroup({
        'name': new FormControl('', [Validators.required,  Validators.minLength(1)]),
        'email': new FormControl('', [Validators.required, ValidateEmail]),
        'phone': new FormControl('', [Validators.required, ValidatePhone]),
      }),
      'parentB': new FormGroup({
        'name': new FormControl('', [Validators.required,  Validators.minLength(1)]),
        'email': new FormControl('', [Validators.required, ValidateEmail]),
        'phone': new FormControl('', [Validators.required, ValidatePhone]),
      }),
      'child': new FormGroup({
        'date': new FormControl(null, Validators.required),
        'isBorn': new FormControl(false, Validators.required),
        'password': new FormControl('', [Validators.required, Validators.minLength(8)])
      }, ValidateBirthdate)
    });
  }


  scrollToTop() {
    const scrollToTop = window.setInterval(() => {
      const pos = window.pageYOffset;
      if (pos > 0) {
        window.scrollTo(0, pos - 40); // how far to scroll on each step
      } else {
        window.clearInterval(scrollToTop);
      }
    }, 16);
  }

}

