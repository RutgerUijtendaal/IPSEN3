import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
import { PageNotFoundComponent } from './pagenotfound/pagenotfound.component';
import {LoginComponent} from './login/login.component';
import {ContactComponent} from './contact/contact.component';
import {PrivacyComponent} from './privacy/privacy.component';
import {RegisterSuccessComponent} from './register/register-success/register-success.component';
import {RegisterNewComponent} from './register/register-new/register-new.component';
import {AdminHomeComponent} from './admin-home/admin-home.component';
import {NoaccessComponent} from './noaccess/noaccess.component';
import {LogoutComponent} from './logout/logout.component';

const routes: Routes = [
  { path: '', component: HomeComponent},
  { path: 'registreren',
    component: RegisterComponent,
    children: [
      {path: '', component: RegisterNewComponent},
      {path: 'succes', component: RegisterSuccessComponent}
    ]},
  { path: 'pagina-niet-gevonden', component: PageNotFoundComponent},
  { path: 'inloggen', component: LoginComponent},
  { path: 'contact', component: ContactComponent },
  { path: 'privacy', component: PrivacyComponent },
  { path: 'beheerder', component: AdminHomeComponent },
  { path: 'forbidden', component: NoaccessComponent },
  { path: 'logout', component: LogoutComponent },
  { path: '**', redirectTo: 'pagina-niet-gevonden'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
