import { Component, Input, OnDestroy, OnInit, Output } from '@angular/core';
import { DilemmaModel } from '../../../../../../shared/models/dilemma.model';
import { StatisticsService } from '../../statistics.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-statistics-dilemma-in-list',
  templateUrl: './dilemma-statistics-in-list.component.html',
  styleUrls: ['./dilemma-statistics-in-list.component.scss']
})
export class DilemmaStatisticsInListComponent implements OnInit, OnDestroy {

  @Input() dilemma: DilemmaModel;

  isActive = false;
  empty = true;
  private subscription: Subscription;

  constructor(public statisticsService: StatisticsService) {
  }

  update() {
    this.empty = this.statisticsService.dilemmas.length === 0;
    const indexOf = this.statisticsService.dilemmas.indexOf(this.dilemma.id);
    this.isActive = indexOf > -1;
  }
  ngOnInit() {
    this.subscription = this.statisticsService.data.subscribe(data => this.update());
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

}
