import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {CardComponent} from './components/card/card.component';
import {ErrorMessageComponent} from './components/error-message/error-message.component';
import {DropdownNavDirective} from './directives/dropdownnav.directive';
import {ChildComponent} from './forms/child/child.component';
import {ParentComponent} from './forms/parent/parent.component';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MDBBootstrapModule} from 'angular-bootstrap-md';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

@NgModule({
  declarations: [
    CardComponent,
    ErrorMessageComponent,
    DropdownNavDirective,
    ChildComponent,
    ParentComponent,
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MDBBootstrapModule.forRoot()
  ],
  exports: [
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    CardComponent,
    ErrorMessageComponent,
    DropdownNavDirective,
    ChildComponent,
    ParentComponent,
    MDBBootstrapModule,
  ]
})
export class SharedModule { }
