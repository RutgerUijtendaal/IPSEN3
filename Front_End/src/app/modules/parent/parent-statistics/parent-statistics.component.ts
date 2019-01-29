import {Component, Input, OnInit} from '@angular/core';
import {ParentDataService} from '../parent-data.service';

@Component({
  selector: 'app-parent-statistics',
  templateUrl: './parent-statistics.component.html',
  styleUrls: ['./parent-statistics.component.scss']
})
export class ParentStatisticsComponent implements OnInit {

  data: [number, number] = [0, 0];

  chartType = 'pie';
  chartDatasets: Array<any> = [
    { data: this.data,
      label: 'Resultaten' }
  ];
  chartLabels: Array<any> = ['Eens', 'Oneens'];
  chartColors: Array<any> = [
    {
      backgroundColor: ['#46BFBD', '#F7464A'],
      hoverBackgroundColor: ['#5AD3D1', '#FF5A5E'],
      borderWidth: 2,
    }
  ];

  chartOptions: any = {
    responsive: true
  };

  constructor(private parentService: ParentDataService) {
    this.parentService.coupleResults[0].forEach((item, index) => {
      const otherAwnserId: number = this.parentService.coupleResults[1][index].answerId;
      if (item.answerId !== 0 && otherAwnserId !== 0 ) {
        if (item.answerId === otherAwnserId) {
          this.data[0] = this.data[0] + 1;
        } else {
          this.data[1] = this.data[1] + 1;
        }
      }
    });
  }

  ngOnInit() {

  }

}
