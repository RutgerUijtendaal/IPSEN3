import { NgModule } from '@angular/core';
import {ParentNewPasswordComponent} from './parent-new-password/parent-new-password.component';
import {AdminNewPasswordComponent} from './admin-new-password/admin-new-password.component';
import {PasswordResetViewComponent} from './password-reset-view/password-reset-view.component';
import {PasswordResetRoutingModule} from './password-reset-routing.module';
import {CommonModule} from '@angular/common';
import {SharedModule} from '../../shared/shared.module';

@NgModule({
  declarations: [
    ParentNewPasswordComponent,
    AdminNewPasswordComponent,
    PasswordResetViewComponent
  ],
  imports: [
    CommonModule,
    PasswordResetRoutingModule,
    SharedModule
  ],
  providers: [
  ]
})
export class PasswordResetModule { }
