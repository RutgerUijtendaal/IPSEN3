import { BrowserModule } from '@angular/platform-browser';
import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegisterComponent } from './register/register.component';
import { ParentComponent } from './form/model/parent/parent.component';
import { ChildComponent } from './form/model/child/child.component';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { HeaderComponent } from './template/header/header.component';
import { FooterComponent } from './template/footer/footer.component';
import { HomeComponent } from './home/home.component';
import { PageNotFoundComponent } from './pagenotfound/pagenotfound.component';
import { CardComponent } from './widget/card/card.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { NavbarComponent } from './template/header/navbar/navbar.component';
import { DropdownNavDirective } from './dropdownnav.directive';
import { HttpClientModule } from '@angular/common/http';
@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    ParentComponent,
    ChildComponent,
    HeaderComponent,
    FooterComponent,
    HomeComponent,
    CardComponent,
    HomeComponent,
    PageNotFoundComponent,
    NavbarComponent,
    DropdownNavDirective
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    MDBBootstrapModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent],
  schemas: [NO_ERRORS_SCHEMA]
})
export class AppModule { }
