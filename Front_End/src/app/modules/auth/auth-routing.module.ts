import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {RegisterComponent} from './register/register.component';
import {RegisterNewComponent} from './register/register-new/register-new.component';
import {RegisterSuccessComponent} from './register/register-success/register-success.component';
import {UnregisterComponent} from './unregister/unregister.component';
import {UnregisterNewComponent} from './unregister/unregister-new/unregister-new.component';
import {UnregisterSuccessComponent} from './unregister/unregister-success/unregister-success.component';
import {LoginComponent} from './login/login.component';
import {LogoutComponent} from './logout/logout.component';


const routes: Routes = [
  { path: 'registreren',
    component: RegisterComponent,
    children: [
      {path: '', component: RegisterNewComponent},
      {path: 'succes', component: RegisterSuccessComponent}
    ]},
  { path: 'uitschrijven',
    component: UnregisterComponent,
    children: [
      { path: ':token', component: UnregisterNewComponent },
      { path: ':token/succes', component: UnregisterSuccessComponent},
    ]},
  { path: 'inloggen', component: LoginComponent},
  { path: 'logout', component: LogoutComponent },

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AuthRoutingModule { }
