import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ParentHomeComponent } from './parent-home/parent-home.component';
import {SharedModule} from '../../shared/shared.module';
import {ParentRoutingModule} from './parent-routing.module';
import {ParentDataService} from './parent-data.service';
import { ParentNavComponent } from './parent-nav/parent-nav.component';
import { ParentResultComponent } from './parent-result/parent-result.component';
import { ParentMeComponent } from './parent-me/parent-me.component';
import { ParentDilemmaListComponent } from './parent-result/parent-dilemma-list/parent-dilemma-list.component';
import { ParentDilemmaDetailComponent } from './parent-result/parent-dilemma-detail/parent-dilemma-detail.component';
import { ParentDilemmaInListComponent } from './parent-result/parent-dilemma-list/parent-dilemma-in-list/parent-dilemma-in-list.component';
import { ParentDetailsComponent } from './parent-me/parent-details/parent-details.component';
import {ParentDilemmaListService} from "./parent-result/parent-dilemma-list.service";
import {ParentStatisticsComponent} from "./parent-statistics/parent-statistics.component";
import { ParentDilemmaNewComponent } from './parent-me/parent-dilemma-new/parent-dilemma-new.component';
import { ParentChildBornComponent } from './parent-me/parent-child-born/parent-child-born.component';
import { BornVerifyComponent } from './parent-me/parent-child-born/born-verify/born-verify.component';
import { ParentDeleteComponent } from './parent-me/parent-delete/parent-delete.component';
import { DeleteVerifyComponent } from './parent-me/parent-delete/delete-verify/delete-verify.component';

@NgModule({
  declarations: [
    ParentHomeComponent,
    ParentNavComponent,
    ParentResultComponent,
    ParentMeComponent,
    ParentDilemmaListComponent,
    ParentDilemmaDetailComponent,
    ParentDilemmaInListComponent,
    ParentDetailsComponent,
    ParentStatisticsComponent,
    ParentDilemmaNewComponent,
    ParentChildBornComponent,
    BornVerifyComponent,
    ParentDeleteComponent,
    DeleteVerifyComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    ParentRoutingModule
  ],
  providers: [
    ParentDataService,
    ParentDilemmaListService
  ]
})
export class ParentModule { }
