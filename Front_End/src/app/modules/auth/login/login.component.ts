import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ValidateEmail} from '../../../shared/validators/email.validator';
import {Router} from '@angular/router';
import {AuthenticationService} from '../../../core/auth/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  loginLoading = false;
  showError = false;
  errorMessage = '';
  adminForm: FormGroup;


  constructor(private router: Router, private authenticationService: AuthenticationService) {
  }

  private initForm() {
    this.adminForm = new FormGroup({
      'email': new FormControl('', [Validators.required, ValidateEmail]),
      // TODO
      'password': new FormControl('', [Validators.required, Validators.minLength(4)])
    });
  }

  submitForm(): void {
    this.loginLoading = true;

    this.authenticationService.login(this.adminForm.value.email, this.adminForm.value.password).subscribe(
      (data: any) => {
        this.loginLoading = false;
        if (data != null) {
          this.authenticationService.setLogin(data);
          if (this.authenticationService.accountModel.type === 'user') {
            this.router.navigateByUrl('/gebruiker/me');
          } else {
            this.router.navigateByUrl('/');

          }
        }
      },
      (error: any) => {
        this.loginLoading = false;
        this.errorMessage = 'Gebruiker bestaat niet, controleer uw inlog gegevens.';
        this.showError = true;
    });
  }

  ngOnInit() {
    this.initForm();
  }

}
