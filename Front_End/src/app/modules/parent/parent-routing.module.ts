import {RouterModule, Routes} from '@angular/router';
import {NgModule} from '@angular/core';
import {ParentHomeComponent} from './parent-home/parent-home.component';
import {ParentResultComponent} from './parent-result/parent-result.component';
import {ParentMeComponent} from './parent-me/parent-me.component';
import {ParentStatisticsComponent} from "./parent-me/parent-statistics/parent-statistics.component";

const routes: Routes = [
  { path: 'gebruiker',
    component: ParentHomeComponent,
    children: [
      { path: 'me', component: ParentMeComponent},
      { path: 'antwoorden', component: ParentResultComponent},
      { path: 'statistieken', component: ParentStatisticsComponent }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ParentRoutingModule { }
