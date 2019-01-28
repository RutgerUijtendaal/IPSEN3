import { Component, OnInit } from '@angular/core';
import { StatisticsService } from '../statistics.service';

@Component({
  selector: 'app-day-chart',
  templateUrl: './day-chart.component.html',
  styleUrls: ['./day-chart.component.scss']
})
export class DayChartComponent implements OnInit {

  public chartOptions: any = {
    responsive: true
  };

  public chartType = 'bar';

  public chartDatasets: Array<any> = [
    { data: [65, 59, 80, 81, 56, 55, 40], label: 'Beantwoord op' }
  ];

  public chartLabels: Array<any> = ['Zondag', 'Maandag', 'Dinsdag', 'Woensdag', 'Donderdag', 'Vrijdag', 'Zaterdag'];

  public chartColors: Array<any> = [
    {
      backgroundColor: 'rgba(0, 137, 132, .2)',
      borderColor: 'rgba(0, 10, 130, .7)',
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
      completeDataset[dateTime.getDay()] += 1;
    }
    this.chartDatasets = [
      {data: completeDataset}
    ];
  }

  ngOnInit() {
    this.statisticsService.data.subscribe(data => this.loadData(data));
  }

}
