import { Component, OnInit } from '@angular/core';
import { StatisticsService } from '../statistics.service';

@Component({
  selector: 'app-answer-chart',
  templateUrl: './answer-chart.component.html',
  styleUrls: ['./answer-chart.component.scss']
})
export class AnswerChartComponent implements OnInit {

  public chartType = 'pie';

  public chartDatasets: Array<any> = [
    { data: [0], label: 'Antwoorden' }
  ];

  public chartLabels: Array<any> = ['Data wordt geladen'];

  public chartOptions: any = {
    responsive: true
  };

  constructor(private statisticsService: StatisticsService) {
    statisticsService.data.subscribe(data => this.loadData(data));
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
        completeDataset[result.answerId].value += 1;
      }
    }
    const values = [];
    const labels = [];
    for (let i = 0; i < answers.length; i++) {
      let answer;
      answer = answers[i];
      if (completeDataset[answer.id].value !== undefined) {
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
