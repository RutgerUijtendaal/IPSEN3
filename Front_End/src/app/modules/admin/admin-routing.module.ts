import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AdminHomeComponent} from './admin-home/admin-home.component';
import {CoupleViewComponent} from './admin-home/couple-view/couple-view.component';
import {DilemmaViewComponent} from './admin-home/dilemma-view/dilemma-view.component';
import {AdminViewComponent} from './admin-home/admin-view/admin-view.component';


const routes: Routes = [
  { path: 'beheerder/ouders', component: CoupleViewComponent },
  { path: 'beheerder/dilemmas', component: DilemmaViewComponent },
  { path: 'beheerder/beheerders', component: AdminViewComponent }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
