import {Injectable} from '@angular/core';
import {DilemmaModel} from '../../../shared/models/dilemma.model';
import {AnswerModel} from '../../../shared/models/answer.model';
import {Period} from '../../../shared/models/periode.model';
import {ResultModel} from '../../../shared/models/result.model';
import {ParentService} from '../parent.service';

@Injectable()
export class ParentDilemmaListService {

  periods: Period[] = [];

  filteredDilemmas: DilemmaModel[] = [];

  periodFilter: string;
  activeDilemma: DilemmaModel = null;
  parentAnswer: AnswerModel = null;
  partnerAnswer: AnswerModel = null;

  constructor(private parentService: ParentService) {

    this.setupPeriodes();
    this.setupFilter();
  }

  setupPeriodes() {
    this.periods.push(new Period('voor', 'Voor Geboorte'));
    this.periods.push(new Period('na', 'Na Geboorte'));
  }

  setupFilter() {
    this.periodFilter = this.periods[0].link;

    this.filterDilemmas(this.periodFilter);

    if (this.filteredDilemmas.length < 1) {
      this.filterDilemmas('na');
    }
  }

  filterDilemmas(period: string) {
    this.periodFilter = period;
    this.filteredDilemmas = this.parentService.activeDilemmas.filter(i => i.period === period);
  }

  setActiveDilemma(dilemma: DilemmaModel) {
    this.activeDilemma = dilemma;
    this.parentAnswer = null;
    this.partnerAnswer = null;

    console.log(this.activeDilemma);
    const possibleAnswers = this.parentService.answers.filter(i => i.dilemmaId === dilemma.id);

    const parentResult: ResultModel = this.parentService.coupleResults[0].find(function(e: ResultModel) {
      return e.answerId === possibleAnswers[0].id || e.answerId === possibleAnswers[1].id;
    });

    if (parentResult) {
      this.parentAnswer = this.parentService.answers.find(i => i.id === (parentResult !== undefined ? parentResult.answerId : 0));
    }

    const partnerResult: ResultModel = this.parentService.coupleResults[1].find(function(e: ResultModel) {
      return e.answerId === possibleAnswers[0].id || e.answerId === possibleAnswers[1].id;
    });

    if (parentResult) {
      this.partnerAnswer = this.parentService.answers.find(i => i.id === (parentResult !== undefined ? partnerResult.answerId : 0));
    }
  }
}
