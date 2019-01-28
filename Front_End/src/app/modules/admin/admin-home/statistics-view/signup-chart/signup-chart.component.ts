import { Component, OnInit } from '@angular/core';
import { StatisticsService } from '../statistics.service';

@Component({
  selector: 'app-signup-chart',
  templateUrl: './signup-chart.component.html',
  styleUrls: ['./signup-chart.component.scss']
})
export class SignupChartComponent implements OnInit {

  public chartType = 'pie';

  public chartDatasets: Array<any> = [
    { data: [0], label: 'Antwoorden' }
  ];

  public chartLabels: Array<any> = ['Voor Geboorte', 'Na Geboorte'];

  public chartOptions: any = {
    responsive: true
  };
  public chartColors: Array<any> = [
    {
      backgroundColor: ['#F7464A', '#46BFBD', '#FDB45C', '#949FB1', '#4D5360'],
      hoverBackgroundColor: ['#FF5A5E', '#5AD3D1', '#FFC870', '#A8B3C5', '#616774'],
      borderWidth: 2,
    }
  ];

  constructor(private statisticsService: StatisticsService) {
  }

  loadData(data) {
    const values = [0, 0];
    const couples = data.filteredCouples;
    const childeren = data.filteredChildren;
    for (let i = 0; i < couples.length; i++) {
      for (let j = 0; j < childeren.length; j++) {
        if (couples[i].id === childeren[j].coupleId) {
          if ((couples[i].signupDate > childeren[j].date) && childeren[j].isBorn) {
            values[0] += 1;
          } else {
            values[1] += 1;
          }
        }
      }
    }
    this.chartDatasets = [
      {data: values}
    ];
  }

  ngOnInit() {
    this.statisticsService.data.subscribe(data => this.loadData(data));
  }

}
