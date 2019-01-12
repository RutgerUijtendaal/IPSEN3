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

  saveDilemma() {
  }

  deleteDilemma() {

  }

}
