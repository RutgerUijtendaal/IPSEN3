import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AppComponent } from '../../../../../app.component';
import { DilemmaListService } from './dilemma-list-service';
import { DilemmaModel } from '../../../../../shared/models/dilemma.model';
import { AnswerModel } from '../../../../../shared/models/answer.model';
import {DilemmaViewService} from '../dilemma-view-service';
import { CdkDragDrop, moveItemInArray } from '@angular/cdk/drag-drop';

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
  newDilemmaButtonText: string;
  newDilemmaButtonClass: string;
  periodes: Periode[] = [];

  constructor(private listService: DilemmaListService,
              private httpClient: HttpClient,
              private viewService: DilemmaViewService) {
    listService.searchQuery.subscribe(search => this.updateList(search));
    viewService.delete.subscribe(dilemma => this.deleteDilemmaFromList());
    this.shownDilemmas = [];
    this.periodes.push(new Periode('voor', 'Voor Geboorte'));
    this.periodes.push(new Periode('na', 'Na Geboorte'));
    this.loadDilemmas('voor');
    this.loadAnswers();
    this.resetNewDilemmaButton();
  }

  loadDilemmas(periode: string) {
    this.allDilemmas = [];
    this.httpClient.get(this.URL + '/dilemma/' + periode).subscribe(data =>
      this.createDilemmaRecords(data as DilemmaModel[])
    );
    this.allDilemmas.sort(dilemma => dilemma.weekNr);
  }

  loadAnswers() {
    this.allAnswers = [];
    this.httpClient.get(this.URL + '/answer/').subscribe(data =>
      this.createAnswerRecords(data as AnswerModel[])
    );
  }

  createDilemmaRecords(data: DilemmaModel[]) {
    data.forEach(dilemma => {
        const id = dilemma.id;
        const weekNr = dilemma.weekNr;
        const theme = dilemma.theme;
        const feedback = dilemma.feedback;
        const periode = dilemma.periode;
        const dilemmaModel: DilemmaModel = new DilemmaModel(id, weekNr, theme, feedback, periode);
        this.allDilemmas.push(dilemmaModel);
      }
    );
    this.updateList('');
  }

  matchAnswerDilemma(dilemma: DilemmaModel) {
    let first = true;
    this.allAnswers.forEach(answer => {
      if (answer.dilemmaId === dilemma.id) {
        if (first) {
          this.viewService.answer1 = answer;
          first = false;
        } else {
          this.viewService.answer2 = answer;
          return;
        }
      }
    });
  }

  dilemmaClicked(dilemma: DilemmaModel) {
    this.currentSelectedDilemma = dilemma;
    this.viewService.dilemma = dilemma;
    this.matchAnswerDilemma(dilemma);
    this.viewService.click.next(0);
    this.allAnswers.sort(   (a, b) => (a.id > b.id) ? 1 : 0);
  }

  createAnswerRecords(data: AnswerModel[]) {
    data.forEach(answer => {
        const id = answer.id;
        const dilemmaId = answer.dilemmaId;
        const url = answer.url;
        const text = answer.text;
        const answerModel: AnswerModel = new AnswerModel(id, dilemmaId, url, text);
        this.allAnswers.push(answerModel);
      }
    );
    this.updateList('');
  }

  updateList(searchQuery: string) {
    searchQuery = searchQuery.toLocaleLowerCase();
    this.oldSearch = searchQuery;
    this.shownDilemmas = this.allDilemmas.filter( dilemma =>
      String(dilemma.weekNr).includes(searchQuery) ||
      dilemma.theme.toLocaleLowerCase().includes(searchQuery)
    );
    this.shownDilemmas.sort((a, b) => (a.weekNr > b.weekNr) ? 1 : 0);
    this.allAnswers.sort(   (a, b) => (a.id > b.id) ? 1 : 0);
  }

  deleteAnswersFromList() {
    this.allAnswers.splice(this.allAnswers.findIndex(a => a.dilemmaId === this.currentSelectedDilemma.id), 1);
    this.allAnswers.splice(this.allAnswers.findIndex(a => a.dilemmaId === this.currentSelectedDilemma.id), 1);
  }

  deleteDilemmaFromList() {
    this.allDilemmas.splice(this.allDilemmas.findIndex(d => d.id === this.currentSelectedDilemma.id), 1);
    this.deleteAnswersFromList();
    this.updateList(this.oldSearch);
  }

  resetNewDilemmaButton() {
    this.newDilemmaButtonText = 'Nieuw dilemma';
    this.newDilemmaButtonClass = 'primary';
  }

  unfinishedDilemma() {
    for (const dilemma of this.allDilemmas) {
      if ((dilemma.theme.length === 0) || (dilemma.feedback.length === 0)) {
        this.newDilemmaButtonText = 'Onafgemaakt dilemma';
        this.newDilemmaButtonClass = 'danger';
        setTimeout(() => {
          this.resetNewDilemmaButton();
        }, 1500);
        return dilemma;
      }
    }
    return false;
  }

  newDilemma() {
    this.viewService.click.next(0);
    const val = this.unfinishedDilemma();
    if (val) {
      this.currentSelectedDilemma = val;
      this.viewService.dilemma = val;
      this.matchAnswerDilemma(val);
      return;
    }
    const newDilemma = new DilemmaModel(-1, 0, '', '', '');
    const newAnswer1 = new AnswerModel(-2, -1, null, null);
    const newAnswer2 = new AnswerModel(-3, -1, null, null);
    this.viewService.dilemma = newDilemma;
    this.viewService.answer1 = newAnswer1;
    this.viewService.answer2 = newAnswer2;
    this.allDilemmas.push(newDilemma);
    this.allAnswers.push(newAnswer1);
    this.allAnswers.push(newAnswer2);
    this.currentSelectedDilemma = newDilemma;
    this.updateList(this.oldSearch);
  }

  ngOnInit() {
  }

  drop(event: CdkDragDrop<DilemmaModel[]>) {
    moveItemInArray(this.allDilemmas, event.previousIndex, event.currentIndex);
    this.updateList('');
    this.shownDilemmas.forEach((dilemma, index) => {
      dilemma.weekNr = index + 1;
      this.httpClient.put(this.URL + '/dilemma/' + dilemma.id, dilemma).subscribe(res => {
      });
    });
  }

  onChange(value: string) {
    this.loadDilemmas(value);
  }
}

class Periode {
  constructor(public link: string, public name: string) {
  }

}
