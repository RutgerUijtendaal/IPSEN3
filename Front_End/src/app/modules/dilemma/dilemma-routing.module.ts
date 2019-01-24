import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DilemmaViewComponent } from './dilemma-view/dilemma-view.component';


const routes: Routes = [
  { path: 'dilemma/beantwoord/:token', component: DilemmaViewComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DilemmaRoutingModule { }
