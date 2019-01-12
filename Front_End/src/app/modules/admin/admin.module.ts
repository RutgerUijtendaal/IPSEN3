import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../../shared/shared.module';
import { AdminHomeComponent } from './admin-home/admin-home.component';
import { CoupleListComponent } from './admin-home/couple-view/couple-list/couple-list.component';
import { CoupleListSearchbarComponent } from './admin-home/couple-view/couple-list-searchbar/couple-list-searchbar.component';
import { CoupleInListComponent } from './admin-home/couple-view/couple-list/couple-in-list/couple-in-list.component';
import { CoupleViewComponent } from './admin-home/couple-view/couple-view.component';
import { CoupleListService } from './admin-home/couple-view/couple-list-service';
import { AdminRoutingModule } from './admin-routing.module';
import { AdminListComponent } from './admin-home/admin-view/admin-list/admin-list.component';
import { AdminViewComponent } from './admin-home/admin-view/admin-view.component';
import { AdminListSearchbarComponent } from './admin-home/admin-view/admin-list-searchbar/admin-list-searchbar.component';
import { DilemmaViewComponent } from './admin-home/dilemma-view/dilemma-view.component';
import { DilemmaListComponent } from './admin-home/dilemma-view/dilemma-list/dilemma-list.component';
import { DilemmaListSearchbarComponent } from './admin-home/dilemma-view/dilemma-list-searchbar/dilemma-list-searchbar.component';
import { DilemmaDetailComponent } from './admin-home/dilemma-view/dilemma-detail/dilemma-detail.component';
import { AdminDetailComponent } from './admin-home/admin-view/admin-detail/admin-detail.component';
import { DilemmaInListComponent } from './admin-home/dilemma-view/dilemma-list/dilemma-in-list/dilemma-in-list.component';
import { AdminInListComponent } from './admin-home/admin-view/admin-list/admin-in-list/admin-in-list.component';
import { DilemmaListService } from './admin-home/dilemma-view/dilemma-list/dilemma-list-service';
import {DilemmaViewService} from './admin-home/dilemma-view/dilemma-view-service';
import {FormsModule} from '@angular/forms';

@NgModule({
  declarations: [
    AdminHomeComponent,
    CoupleListComponent,
    CoupleListSearchbarComponent,
    CoupleInListComponent,
    CoupleViewComponent,
    AdminListComponent,
    AdminViewComponent,
    AdminListSearchbarComponent,
    DilemmaViewComponent,
    DilemmaListComponent,
    DilemmaListSearchbarComponent,
    DilemmaDetailComponent,
    AdminDetailComponent,
    DilemmaInListComponent,
    AdminInListComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    SharedModule,
    AdminRoutingModule
  ],
  providers: [
    CoupleListService,
    DilemmaListService,
    DilemmaViewService
  ]
})
export class AdminModule {
}
