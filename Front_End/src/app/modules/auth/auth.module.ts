import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {LoginComponent} from './login/login.component';
import {LoginCardComponent} from './login/login-card/login-card.component';
import {LogoutComponent} from './logout/logout.component';
import {RegisterComponent} from './register/register.component';
import {RegisterNewComponent} from './register/register-new/register-new.component';
import {RegisterNewService} from './register/register-new/register-new.service';
import {UnregisterNewComponent} from './unregister/unregister-new/unregister-new.component';
import {UnregisterComponent} from './unregister/unregister.component';
import {UnregisterSuccessComponent} from './unregister/unregister-success/unregister-success.component';
import {UnregisterService} from './unregister/unregister-new/unregister.service';
import {RegisterSuccessComponent} from './register/register-success/register-success.component';
import {SharedModule} from '../../shared/shared.module';
import {AuthRoutingModule} from './auth-routing.module';

@NgModule({
  declarations: [
    LoginCardComponent,
    LoginComponent,
    LogoutComponent,
    RegisterComponent,
    RegisterNewComponent,
    RegisterSuccessComponent,
    UnregisterComponent,
    UnregisterNewComponent,
    UnregisterSuccessComponent,
  ],
  providers: [
    UnregisterService,
    RegisterNewService
  ],
  imports: [
    CommonModule,
    SharedModule,
    AuthRoutingModule
  ]
})
export class AuthModule { }
