import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {SharedModule} from '../../shared/shared.module';
import {AdminHomeComponent} from './admin-home/admin-home.component';
import {CoupleListComponent} from './admin-home/couple-view/couple-list/couple-list.component';
import {CoupleListSearchbarComponent} from './admin-home/couple-view/couple-list-searchbar/couple-list-searchbar.component';
import {CoupleInListComponent} from './admin-home/couple-view/couple-list/couple-in-list/couple-in-list.component';
import {CoupleViewComponent} from './admin-home/couple-view/couple-view.component';
import {CoupleListService} from './admin-home/couple-view/couple-list-service';
import {AdminRoutingModule} from './admin-routing.module';

@NgModule({
  declarations: [
    AdminHomeComponent,
    CoupleListComponent,
    CoupleListSearchbarComponent,
    CoupleInListComponent,
    CoupleViewComponent,
  ],
  imports: [
    CommonModule,
    SharedModule,
    AdminRoutingModule
  ],
  providers: [
    CoupleListService
  ]
})
export class AdminModule { }
