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
import { DragDropModule } from '@angular/cdk/drag-drop';
import {AdminListService} from './admin-home/admin-view/admin-list/admin-list.service';
import {AdminViewService} from './admin-home/admin-view/admin-view.service';
import { StatisticsViewComponent } from './admin-home/statistics-view/statistics-view.component';
import { StatisticsService } from './admin-home/statistics-view/statistics.service';
import { AnswerChartComponent } from './admin-home/statistics-view/anwser-chart/answer-chart.component';
import { TimeChartComponent } from './admin-home/statistics-view/time-chart/time-chart.component';
import { DayChartComponent } from './admin-home/statistics-view/day-chart/day-chart.component';
import { SignupChartComponent } from './admin-home/statistics-view/signup-chart/signup-chart.component';
import { CoupleStatisticsInListComponent } from './admin-home/statistics-view/couple-statistics-list/couple-statistics-in-list/couple-statistics-in-list.component';
import { CoupleStatisticsListComponent } from './admin-home/statistics-view/couple-statistics-list/couple-statistics-list.component';
import { DilemmaStatisticsListComponent } from './admin-home/statistics-view/dilemma-statistics-list/dilemma-statistics-list.component';
import { DilemmaStatisticsInListComponent } from './admin-home/statistics-view/dilemma-statistics-list/dilemma-in-list/dilemma-statistics-in-list.component';
import { SpeedChartComponent } from './admin-home/statistics-view/speed-chart/speed-chart.component';
import {DilemmaViewHttpService} from './admin-home/dilemma-view/dilemma-view-http.service';
import {CoupleViewHttpService} from './admin-home/couple-view/couple-view-http.service';
import {AdminViewHttpService} from './admin-home/admin-view/admin-view-http.service';
import { StatisticsNavComponent } from './admin-home/statistics-view/statistics-nav/statistics-nav.component';
import { FilterNavComponent } from './admin-home/statistics-view/filter-nav/filter-nav.component';
import { CoupleStatisticsFilterComponent } from './admin-home/statistics-view/couple-statistics-list/couple-statistics-filter/couple-statistics-filter.component';
import { RelevancyChartComponent } from './admin-home/statistics-view/relevancy-chart/relevancy-chart.component';

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
    AdminInListComponent,
    StatisticsViewComponent,
    AnswerChartComponent,
    TimeChartComponent,
    DayChartComponent,
    SignupChartComponent,
    CoupleStatisticsInListComponent,
    CoupleStatisticsListComponent,
    DilemmaStatisticsListComponent,
    DilemmaStatisticsInListComponent,
    SpeedChartComponent,
    StatisticsNavComponent,
    FilterNavComponent,
    CoupleStatisticsFilterComponent,
    RelevancyChartComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    SharedModule,
    AdminRoutingModule,
    DragDropModule,
  ],
  providers: [
    CoupleListService,
    DilemmaListService,
    DilemmaViewService,
    AdminListService,
    AdminViewService,
    StatisticsService,
    DilemmaViewHttpService,
    CoupleViewHttpService,
    AdminViewHttpService
  ]
})
export class AdminModule {
}
