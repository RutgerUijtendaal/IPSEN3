import { Component, OnInit } from '@angular/core';
import {StatisticsService} from '../statistics.service';
import {DilemmaModel} from '../../../../../shared/models/dilemma.model';

@Component({
  selector: 'app-relevancy-chart',
  templateUrl: './relevancy-chart.component.html',
  styleUrls: ['./relevancy-chart.component.scss']
})
export class RelevancyChartComponent implements OnInit {

  public chartOptions: any = {
    responsive: true,
    scales: {
      xAxes: [{
        scaleLabel: {
          display: true,
          labelString: 'Dilemma'
        }
      }]
    },

  };

  public chartType = 'bar';

  public chartDatasets: Array<any> = [
    { data: [], label: '', backgroundColor: ['red', 'green', 'green']}
  ];

  public chartLabels: Array<any> = [];

  public chartColors: Array<any> = [
    {
      backgroundColor: [],
    }
  ];


  constructor(private statisticsService: StatisticsService) { }

  ngOnInit() {
    this.statisticsService.data.subscribe(data => this.loadData(data));
  }

  loadData(data) {
    let ratings;
    ratings = data.filteredRatings;
    const completeDataset = [];
    const labels = [];

    for(let i = 0; i < ratings.length; i++) {
      let rating;
      rating = ratings[i];
      // Build Labels
      let label = labels.find(id => id === rating.dilemmaId);
      if(!label) {
        labels.push(rating.dilemmaId);
      }

      let index = labels.findIndex(id => id === rating.dilemmaId);

      // Build Result
      if(completeDataset[index] === undefined) {
        completeDataset[index] = rating.rating;
      } else {
        if (rating.rating === -1) {
          completeDataset[index] -= 1;
        } else {
          completeDataset[index] += 1;
        }
      }
    }

    console.log(completeDataset);

    const myColors = [];
    for(let i = 0; i < labels.length; i++) {
      if(completeDataset[i] < 0) {
        myColors[i] = 'red';
      } else {
        myColors[i] = 'green';

      }
    }

    this.chartLabels = labels;

    this.chartDatasets = [
      {
        label: 'Relevantie',
        data: completeDataset,
      }
    ];

    this.chartColors = [
      {
        backgroundColor: myColors
      }
    ]

  }


}
