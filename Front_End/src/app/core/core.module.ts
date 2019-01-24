import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {PageNotFoundComponent} from './pages/pagenotfound/pagenotfound.component';
import {NoaccessComponent} from './noaccess/noaccess.component';
import {HeaderComponent} from './header/header.component';
import {NavbarComponent} from './header/navbar/navbar.component';
import {FooterComponent} from './footer/footer.component';
import {SharedModule} from '../shared/shared.module';
import {CoreRoutingModule} from './core-routing.module';
import {AuthenticationService} from './auth/authentication.service';
import {AuthenticationInterceptor} from './auth/authentication.interceptor';
import {ErrorInterceptor} from './auth/error.Interceptor';
import { MDBBootstrapModule } from 'angular-bootstrap-md';

@NgModule({
  declarations: [
    PageNotFoundComponent,
    NoaccessComponent,
    HeaderComponent,
    NavbarComponent,
    FooterComponent,
  ],
  imports: [
    CommonModule,
    SharedModule,
    CoreRoutingModule,
    MDBBootstrapModule.forRoot()
  ],
  providers: [
    AuthenticationService,
    AuthenticationInterceptor,
    ErrorInterceptor
  ],
  exports: [
    PageNotFoundComponent,
    NoaccessComponent,
    HeaderComponent,
    NavbarComponent,
    FooterComponent,
  ]
})
export class CoreModule { }
