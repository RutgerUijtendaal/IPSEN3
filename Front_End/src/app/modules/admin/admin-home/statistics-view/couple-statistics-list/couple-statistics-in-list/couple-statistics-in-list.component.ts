import {Component, EventEmitter, Input, OnDestroy, OnInit, Output} from '@angular/core';
import {CoupleModel} from '../../../../../../shared/models/couple.model';
import { StatisticsService } from '../../statistics.service';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-statistics-couple-in-list',
  templateUrl: './couple-statistics-in-list.component.html',
  styleUrls: ['./couple-statistics-in-list.component.scss']
})
export class CoupleStatisticsInListComponent implements OnInit, OnDestroy {

  @Input() couple: CoupleModel;
  isActive = false;
  empty = true;
  private subscription: Subscription;


  constructor(public statisticsService: StatisticsService) {
  }

  ngOnInit() {
    this.subscription = this.statisticsService.data.subscribe(data => this.update());
  }

  update() {
    this.empty = this.statisticsService.couples.length === 0;
    const indexOf = this.statisticsService.couples.indexOf(this.couple.coupleId);
    this.isActive = indexOf > -1;
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  // change() {
  //   this.empty = this.statisticsService.couples.length === 0;
  //
  //   if (!this.checked) {
  //     this.statisticsService.couples.push(this.couple.coupleId);
  //     this.checked = true;
  //   } else {
  //     this.checked = false;
  //     const indexOf = this.statisticsService.couples.indexOf(this.couple.coupleId);
  //     this.statisticsService.couples.splice(indexOf, 1);
  //   }
  //   this.statisticsService.update();
  // }
}
