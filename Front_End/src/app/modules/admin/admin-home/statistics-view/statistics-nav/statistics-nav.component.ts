import { Component, OnInit } from '@angular/core';
import {StatisticsService} from '../statistics.service';

@Component({
  selector: 'app-statistics-nav',
  templateUrl: './statistics-nav.component.html',
  styleUrls: ['./statistics-nav.component.scss']
})
export class StatisticsNavComponent implements OnInit {

  constructor(public statisticsService: StatisticsService) { }

  ngOnInit() {
    this.statisticsService.activeCharts.push(1)
  }

  updateFilter(chartNr: number) {
    if(this.statisticsService.activeCharts.includes(chartNr)) {
      this.statisticsService.activeCharts.splice(this.statisticsService.activeCharts.indexOf(chartNr), 1)
    } else {
      this.statisticsService.activeCharts.push(chartNr);
    }
  }

}
