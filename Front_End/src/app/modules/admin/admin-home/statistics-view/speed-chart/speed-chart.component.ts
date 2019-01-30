import { Component, OnInit } from '@angular/core';
import { StatisticsService } from '../statistics.service';

@Component({
  selector: 'app-speed-chart',
  templateUrl: './speed-chart.component.html',
  styleUrls: ['./speed-chart.component.scss']
})
export class SpeedChartComponent implements OnInit {

  public chartOptions: any = {
    responsive: true,
    scales: {
      xAxes: [{
        scaleLabel: {
          display: true,
          labelString: 'Uren'
        }
      }]
    }
  };

  public chartType = 'bar';

  public chartDatasets: Array<any> = [
    { data: [65, 59, 80, 81, 56, 55, 40], label: 'Beantwoord na' }
  ];

  public chartLabels: Array<any> = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23];

  constructor(private statisticsService: StatisticsService) {

  }

  loadData(data) {
    let results;
    results = data.filteredResults;
    const completeDataset = [];
    for (let i = 0; i < results.length; i++) {
      let result;
      result = results[i];
      if (result.answeredTime != null) {
        const speed = Math.floor((result.answeredTime - result.sentTime) / (3600000));
        if (speed >= 0 && speed < 169) {
          if (completeDataset[speed] === undefined) {
            completeDataset[speed] = 1;
          } else {
            completeDataset[speed] += 1;
          }
        }
      }
    }
    const labels = [];
    const values = [];
    for (let i = 0; i < results.length; i++)  {
      labels.push(i);
      let result = 0;
      if (results[i] !== undefined) {
        result = results[i];
      }
      values.push(values);
    }

    this.chartLabels = labels;
    this.chartDatasets = [
      {data: completeDataset, label: 'Beantwoord na:'}
    ];
  }

  ngOnInit() {
    this.statisticsService.data.subscribe(data => this.loadData(data));
  }

}
