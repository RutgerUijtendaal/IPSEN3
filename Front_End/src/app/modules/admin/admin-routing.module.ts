import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {CoupleViewComponent} from './admin-home/couple-view/couple-view.component';
import {DilemmaViewComponent} from './admin-home/dilemma-view/dilemma-view.component';
import {AdminViewComponent} from './admin-home/admin-view/admin-view.component';
import {ConfigEditComponent} from './config-edit/config-edit.component';
import { StatisticsViewComponent } from './admin-home/statistics-view/statistics-view.component';


const routes: Routes = [
  { path: 'beheerder/ouders', component: CoupleViewComponent },
  { path: 'beheerder/dilemmas', component: DilemmaViewComponent },
  { path: 'beheerder/dilemma/configuratie', component: ConfigEditComponent },
  { path: 'beheerder/statistieken', component: StatisticsViewComponent },
  { path: 'beheerder/beheerders', component: AdminViewComponent }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
