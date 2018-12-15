import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ValidatePhone} from '../../validators/phone.validator';
import {ValidateEmail} from '../../validators/email.validator';
import {ActivatedRoute, Router, RouterLinkActive} from '@angular/router';
import { RegisterNewService } from './register-new.service';
import { AppComponent } from '../../app.component';

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
  formTitleChild = 'Jullie kind';

  coupleForm: FormGroup;

  constructor(private router: Router, private service: RegisterNewService, private route: ActivatedRoute) {
    console.log(AppComponent.environment);
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
      date: (new Date(this.coupleForm.value.child.date)).getMilliseconds()
    };

    this.service.register(collection).subscribe(
      (message: any) => {
        this.submitLoading = false;
        this.router.navigate(['succes'], {relativeTo: this.route});
      },
      (error: any) => {
        this.submitLoading = false;
        this.errorMessage = 'Er is iets fout gegaan tijdens het inschrijven';
        this.showError = true;
        this.scrollToTop();
      });
  }

  private initForm() {
    // yyyy-MM-dd

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
        'date': new FormControl(null, Validators.required),
        'isBorn': new FormControl(false, Validators.required)
      })
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

