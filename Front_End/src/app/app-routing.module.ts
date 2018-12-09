import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
import {PageNotFoundComponent} from './pagenotfound/pagenotfound.component';

const routes: Routes = [
  { path: '', component: HomeComponent},
  { path: 'registreren', component: RegisterComponent},
  { path: 'pagina-niet-gevonden', component: PageNotFoundComponent},
  { path: '**', redirectTo: 'pagina-niet-gevonden'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
