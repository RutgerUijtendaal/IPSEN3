import { Component, OnInit } from '@angular/core';
import { StatisticsService } from './statistics.service';

@Component({
  selector: 'app-statistics-view',
  templateUrl: './statistics-view.component.html',
  styleUrls: ['./statistics-view.component.scss']
})
export class StatisticsViewComponent implements OnInit {

  public chartType = 'pie';

  public chartDatasets: Array<any> = [
    { data: [300, 50, 100, 40, 120], label: 'My First dataset' }
  ];

  public chartLabels: Array<any> = ['Red', 'Green', 'Yellow', 'Grey', 'Dark Grey'];

  public chartColors: Array<any> = [
    {
      backgroundColor: ['#F7464A', '#46BFBD', '#FDB45C', '#949FB1', '#4D5360'],
      hoverBackgroundColor: ['#FF5A5E', '#5AD3D1', '#FFC870', '#A8B3C5', '#616774'],
      borderWidth: 2,
    }
  ];

  public chartOptions: any = {
    responsive: true
  };

  constructor(private statisticsService: StatisticsService) {
    statisticsService.getData().subscribe(data => {
      return this.loadData(data);
    });
  }

  loadData(data) {
    let answers;
    answers = data.filteredAnswers;
    let results;
    results = data.filteredResults;
    const completeDataset = Array;
    for (let i = 0; i < answers.length; i++) {
      let answer;
      answer = answers[i];
      completeDataset[answer.id] = answer;
    }
    for (let i = 0; i < results.length; i++) {
      let result;
      result = results[i];
      if (completeDataset[result.answerId] === undefined) {
        continue;
      }
      if (completeDataset[result.answerId].value === undefined) {
        completeDataset[result.answerId].value = 1;
      } else {
        completeDataset[result.answerId].value = completeDataset[result.answerId]['value'] + 1;
      }
    }
    const values = [];
    const labels = [];
    for (let i = 0; i < answers.length; i++) {
      let answer;
      answer = answers[i];
      if (completeDataset[answer.id].value !== undefined) {
        console.log(completeDataset[answer.id].value)
        values.push(completeDataset[answer.id].value);
        labels.push(answer.text);
      }
    }
    this.chartDatasets = [
      {data: values}
    ];
    this.chartLabels = labels;
  }

  ngOnInit() {
  }

}
