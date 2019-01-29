import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AdminNewPasswordComponent} from './admin-new-password/admin-new-password.component';
import {ParentNewPasswordComponent} from './parent-new-password/parent-new-password.component';
import {PasswordResetViewComponent} from './password-reset-view/password-reset-view.component';

const routes: Routes = [
  { path: 'beheerder/nieuw-wachtwoord/:token', component: AdminNewPasswordComponent },
  { path: 'wachtwoord-vergeten', component: PasswordResetViewComponent },
  { path: 'ouders/nieuw-wachtwoord/:token', component: ParentNewPasswordComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PasswordResetRoutingModule { }
