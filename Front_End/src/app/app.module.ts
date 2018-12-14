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
import { LoginComponent } from './login/login.component';
import { LoginCardComponent } from './login/login-card/login-card.component';
import { ContactComponent } from './contact/contact.component';
import { HttpClientModule } from '@angular/common/http';
import { RegisterSuccessComponent } from './register/register-success/register-success.component';
import { PrivacyComponent} from './privacy/privacy.component';
import { RegisterNewComponent } from './register/register-new/register-new.component';
import { ErrorMessageComponent } from './widget/error-message/error-message.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { AdminHomeComponent } from './admin-home/admin-home.component';
import { CoupleListComponent } from './admin-home/couple-view/couple-list/couple-list.component';
import { CoupleInListComponent } from './admin-home/couple-view/couple-list/couple-in-list/couple-in-list.component';
import { CoupleListSearchbarComponent } from './admin-home/couple-view/couple-list-searchbar/couple-list-searchbar.component';
import { CoupleViewComponent } from './admin-home/couple-view/couple-view.component';

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
    LoginComponent,
    LoginCardComponent,
    PageNotFoundComponent,
    NavbarComponent,
    DropdownNavDirective,
    PageNotFoundComponent,
    LoginComponent,
    LoginCardComponent,
    PrivacyComponent,
    ContactComponent,
    RegisterSuccessComponent,
    RegisterNewComponent,
    ErrorMessageComponent,
    AdminHomeComponent,
    CoupleListComponent,
    CoupleInListComponent,
    CoupleListSearchbarComponent,
    CoupleViewComponent,
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
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
