import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {CoupleModel} from '../../../../../../shared/models/couple.model';
import { StatisticsService } from '../../statistics.service';

@Component({
  selector: 'app-statistics-couple-in-list',
  templateUrl: './couple-statistics-in-list.component.html',
  styleUrls: ['./couple-statistics-in-list.component.scss']
})
export class CoupleStatisticsInListComponent implements OnInit {

  @Input() couple: CoupleModel;
  checked = false;

  constructor(public statisticsService: StatisticsService) {
  }

  ngOnInit() {
  }

  change() {
    if (!this.checked) {
      this.statisticsService.couples.push(this.couple.coupleId);
      this.checked = true;
    } else {
      this.checked = false;
      const indexOf = this.statisticsService.couples.indexOf(this.couple.coupleId);
      this.statisticsService.couples.splice(indexOf, 1);
    }
    this.statisticsService.update();
  }
}
