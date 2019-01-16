import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ParentHomeComponent } from './parent-home/parent-home.component';
import {SharedModule} from '../../shared/shared.module';
import {ParentRoutingModule} from './parent-routing.module';
import {ParentService} from './parent.service';

@NgModule({
  declarations: [
    ParentHomeComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    ParentRoutingModule
  ],
  providers: [
    ParentService
  ]
})
export class ParentModule { }
