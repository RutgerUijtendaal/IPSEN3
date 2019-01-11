import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AppComponent } from '../../../../../app.component';
import { DilemmaListService } from './dilemma-list-service';
import { DilemmaModel } from '../../../../../shared/models/dilemma.model';
import { AnswerModel } from '../../../../../shared/models/answer.model';

@Component({
  selector: 'app-dilemma-list',
  templateUrl: './dilemma-list.component.html',
  styleUrls: ['./dilemma-list.component.scss']
})
export class DilemmaListComponent implements OnInit {

  URL = AppComponent.environment.server;

  allDilemmas: DilemmaModel[];
  allAnswers: AnswerModel[];
  shownDilemmas: DilemmaModel[];
  oldSearch: string;
  currentSelectedDilemma: DilemmaModel;

  constructor(private service: DilemmaListService, private httpClient: HttpClient) {
    service.searchQuery.subscribe(search => this.updateList(search));
    this.allDilemmas = [];
    this.shownDilemmas = [];
    this.allAnswers = [];
    httpClient.get(this.URL + '/dilemma').subscribe(data =>
      this.createDilemmaRecords(data as DilemmaModel[])
    );
    httpClient.get(this.URL + '/answer').subscribe(data =>
      this.createAnswerRecords(data as AnswerModel[])
    );
  }

  createDilemmaRecords(data: DilemmaModel[]) {
    data.forEach(dilemma => {
        const id = dilemma.id;
        const weekNr = dilemma.weekNr;
        const theme = dilemma.theme;
        const feedback = dilemma.feedback;
        this.allDilemmas.push(new DilemmaModel(id, weekNr, theme, feedback));
      }
    );
    this.updateList('');
  }

  createAnswerRecords(data: AnswerModel[]) {
    data.forEach(answer => {
        const id = answer.id;
        const dilemmaId = answer.id;
        const url = answer.url;
        const text = answer.text;
        this.allAnswers.push(new AnswerModel(id, dilemmaId, url, text));
      }
    );
    this.updateList('');
  }

  updateList(searchQuery: string) {
    this.oldSearch = searchQuery;
    this.shownDilemmas = this.allDilemmas.filter( dilemma =>
      String(dilemma.weekNr).includes(searchQuery) ||
      dilemma.theme.includes(searchQuery)
    );
  }

  confirmDelete() {
    this.allDilemmas.splice(this.allDilemmas.findIndex(d => d.id === this.currentSelectedDilemma.id), 1);
    this.updateList(this.oldSearch);
    this.httpClient.delete(this.URL + '/dilemma/' + this.currentSelectedDilemma.id).subscribe((res) => {});
  }

  deleteRequest(dilemmaModel: DilemmaModel) {
    this.currentSelectedDilemma = dilemmaModel;
  }

  ngOnInit() {
  }

}
