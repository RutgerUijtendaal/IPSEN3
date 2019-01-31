import { Component, OnInit } from '@angular/core';
import {StatisticsService} from './statistics.service';

@Component({
  selector: 'app-statistics-view',
  templateUrl: './statistics-view.component.html',
  styleUrls: ['./statistics-view.component.scss']
})
export class StatisticsViewComponent implements OnInit {

  constructor(public statisticsService: StatisticsService) {

  }

  ngOnInit() {
    this.statisticsService.getData();
  }

}
