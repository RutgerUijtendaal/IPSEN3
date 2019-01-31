import { Component, OnInit } from '@angular/core';
import {StatisticsService} from '../statistics.service';

@Component({
  selector: 'app-filter-nav',
  templateUrl: './filter-nav.component.html',
  styleUrls: ['./filter-nav.component.scss']
})
export class FilterNavComponent implements OnInit {

  constructor(public statisticsService: StatisticsService) { }

  ngOnInit() {
    this.statisticsService.activeFilters.push(1)
  }

  updateFilter(chartNr: number) {
    if(this.statisticsService.activeFilters.includes(chartNr)) {
      this.statisticsService.activeFilters.splice(this.statisticsService.activeFilters.indexOf(chartNr), 1)
    } else {
      this.statisticsService.activeFilters.push(chartNr);
    }
  }

}
