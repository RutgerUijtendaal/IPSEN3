import {Injectable} from '@angular/core';
import {Subject} from 'rxjs';
import {DilemmaModel} from '../../../../shared/models/dilemma.model';
import {AnswerModel} from '../../../../shared/models/answer.model';

@Injectable()
export class DilemmaViewService {

  dilemma: DilemmaModel;
  answer1: AnswerModel;
  answer2: AnswerModel;

  constructor() {
  }

}
