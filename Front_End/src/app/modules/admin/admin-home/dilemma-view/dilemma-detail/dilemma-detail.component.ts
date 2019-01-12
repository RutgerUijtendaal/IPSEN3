import {Component, Input, OnInit} from '@angular/core';
import {DilemmaModel} from '../../../../../shared/models/dilemma.model';
import {AnswerModel} from '../../../../../shared/models/answer.model';
import {DilemmaViewService} from '../dilemma-view-service';

@Component({
  selector: 'app-dilemma-detail',
  templateUrl: './dilemma-detail.component.html',
  styleUrls: ['./dilemma-detail.component.scss']
})
export class DilemmaDetailComponent implements OnInit {

  edittedAnswer1: AnswerModel;
  edittedAnswer2: AnswerModel;
  edittedDilemma: AnswerModel;

  constructor(private service: DilemmaViewService) {
  }

  ngOnInit() {
  }

  resetDetails() {
    (<HTMLInputElement>document.getElementsByClassName('dilemma-theme')[0]).value = this.service.dilemma.theme;
    (<HTMLInputElement>document.getElementsByClassName('dilemma-weeknr')[0]).value = String(this.service.dilemma.weekNr);
    (<HTMLInputElement>document.getElementsByClassName('answer1-text')[0]).value = this.service.answer1.text;
    (<HTMLInputElement>document.getElementsByClassName('answer2-text')[0]).value = this.service.answer2.text;
  }

  saveDilemma() {
  }

  deleteDilemma() {
    this.service.dilemma = null;
    this.service.answer1 = null;
    this.service.answer2 = null;
    this.service.delete.next(this.service.dilemma);
  }

}
