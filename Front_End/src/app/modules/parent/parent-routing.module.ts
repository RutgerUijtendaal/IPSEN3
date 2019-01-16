import {RouterModule, Routes} from '@angular/router';
import {NgModule} from '@angular/core';
import {ParentHomeComponent} from './parent-home/parent-home.component';

const routes: Routes = [
  { path: 'gebruiker', component: ParentHomeComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ParentRoutingModule { }
