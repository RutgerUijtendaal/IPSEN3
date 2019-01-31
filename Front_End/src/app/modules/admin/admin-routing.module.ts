import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {CoupleViewComponent} from './admin-home/couple-view/couple-view.component';
import {DilemmaViewComponent} from './admin-home/dilemma-view/dilemma-view.component';
import {AdminViewComponent} from './admin-home/admin-view/admin-view.component';
import {ConfigEditComponent} from './config-edit/config-edit.component';


const routes: Routes = [
  { path: 'beheerder/ouders', component: CoupleViewComponent },
  { path: 'beheerder/dilemmas', component: DilemmaViewComponent },
  { path: 'beheerder/beheerders', component: AdminViewComponent },
  { path: 'beheerder/dilemma/configuratie', component: ConfigEditComponent }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
