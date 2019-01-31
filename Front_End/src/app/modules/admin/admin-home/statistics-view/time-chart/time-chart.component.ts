import { Component, OnInit } from '@angular/core';
import { StatisticsService } from '../statistics.service';

@Component({
  selector: 'app-time-chart',
  templateUrl: './time-chart.component.html',
  styleUrls: ['./time-chart.component.scss']
})
export class TimeChartComponent implements OnInit {

  public chartOptions: any = {
    responsive: true
  };

  public chartType = 'line';

  public chartDatasets: Array<any> = [
    { data: [65, 59, 80, 81, 56, 55, 40], label: 'Beantwoord om' }
  ];

  public chartLabels: Array<any> = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23];

  public chartColors: Array<any> = [
    {
      backgroundColor: [
        'rgba(255, 159, 64, 0.2)'
      ],
      borderColor: [
        'rgba(255, 159, 64, 1)'
      ],
      borderWidth: 2,
    }
  ];

  constructor(private statisticsService: StatisticsService) {

  }

  loadData(data) {
    let results;
    results = data.filteredResults;
    const completeDataset = [];
    for (let i = 0; i < 24; i++) {
      completeDataset[i] = 0;
    }
    for (let i = 0; i < results.length; i++) {
      let result;
      result = results[i];
      if (result.answeredTime === null) {
        continue;
      }
      const dateTime = new Date(result.answeredTime * 1000);
      completeDataset[dateTime.getHours()] += 1;
    }
    this.chartDatasets = [
      {data: completeDataset}
    ];
  }

  ngOnInit() {
    this.statisticsService.data.subscribe(data => this.loadData(data));
  }

}
