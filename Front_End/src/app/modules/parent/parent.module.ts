import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ParentHomeComponent } from './parent-home/parent-home.component';
import {SharedModule} from '../../shared/shared.module';
import {ParentRoutingModule} from './parent-routing.module';
import {ParentService} from './parent.service';
import { ParentNavComponent } from './parent-nav/parent-nav.component';
import { ParentResultComponent } from './parent-result/parent-result.component';
import { ParentMeComponent } from './parent-me/parent-me.component';
import { ParentDilemmaListComponent } from './parent-result/parent-dilemma-list/parent-dilemma-list.component';
import { ParentDilemmaDetailComponent } from './parent-result/parent-dilemma-detail/parent-dilemma-detail.component';
import { ParentDilemmaInListComponent } from './parent-result/parent-dilemma-list/parent-dilemma-in-list/parent-dilemma-in-list.component';
import { ParentDetailsComponent } from './parent-me/parent-details/parent-details.component';

@NgModule({
  declarations: [
    ParentHomeComponent,
    ParentNavComponent,
    ParentResultComponent,
    ParentMeComponent,
    ParentDilemmaListComponent,
    ParentDilemmaDetailComponent,
    ParentDilemmaInListComponent,
    ParentDetailsComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    ParentRoutingModule
  ],
  providers: [
    ParentService,
  ]
})
export class ParentModule { }
