import { DilemmaModel } from '../../../shared/models/dilemma.model';
import { AnswerModel } from '../../../shared/models/answer.model';

export class DilemmaAnswerDatabag {
  private _dilemma: DilemmaModel;
  private _answers: AnswerModel[];


  get dilemma(): DilemmaModel {
    return this._dilemma;
  }

  get answers(): AnswerModel[] {
    return this._answers;
  }
}
