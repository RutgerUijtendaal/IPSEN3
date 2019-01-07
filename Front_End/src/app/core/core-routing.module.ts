import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {NoaccessComponent} from './noaccess/noaccess.component';
import {PageNotFoundComponent} from './pages/pagenotfound/pagenotfound.component';


const routes: Routes = [
  { path: 'pagina-niet-gevonden', component: PageNotFoundComponent},
  { path: 'forbidden', component: NoaccessComponent },
  { path: '**', redirectTo: 'pagina-niet-gevonden'}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CoreRoutingModule { }
