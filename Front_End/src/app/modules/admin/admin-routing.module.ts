import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {CoupleViewComponent} from './admin-home/couple-view/couple-view.component';
import {DilemmaViewComponent} from './admin-home/dilemma-view/dilemma-view.component';
import {AdminViewComponent} from './admin-home/admin-view/admin-view.component';
import {AdminNewPasswordComponent} from './admin-home/admin-new-password/admin-new-password.component';


const routes: Routes = [
  { path: 'beheerder/ouders', component: CoupleViewComponent },
  { path: 'beheerder/dilemmas', component: DilemmaViewComponent },
  { path: 'beheerder/beheerders', component: AdminViewComponent },
  { path: 'beheerder/nieuw-wachtwoord/:token', component: AdminNewPasswordComponent }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
