import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {AuthenticationService} from '../../core/auth/authentication.service';
import {AccountModel} from '../../shared/models/account.model';
import {CoupleModel} from '../../shared/models/couple.model';
import {AppComponent} from '../../app.component';
import {map, mergeMap, take} from 'rxjs/operators';
import {forkJoin} from 'rxjs';
import {ParentModel} from '../../shared/models/parent.model';
import {ResultModel} from '../../shared/models/result.model';
import {AnswerModel} from '../../shared/models/answer.model';
import {DilemmaModel} from '../../shared/models/dilemma.model';

@Injectable()
export class ParentService {

  private URL = AppComponent.environment.server;

  private accountModel: AccountModel;

  couple: CoupleModel;
  coupleResults: [ResultModel[], ResultModel[]] = [[], []];
  dilemmas: DilemmaModel[] = [];
  answers: AnswerModel[] = [];

  periods: Period[] = [];
  activeDilemmas: DilemmaModel[] = [];
  filteredDilemmas: DilemmaModel[] = [];

  periodFilter: string;
  activeDilemma: DilemmaModel = null;
  parentAnswer: AnswerModel = null;
  partnerAnswer: AnswerModel = null;

  coupleIsLoading = false;
  resultsIsLoading = false;

  constructor(private authenticationService: AuthenticationService,
              private httpClient: HttpClient) {
    this.accountModel = authenticationService.accountModel;
    this.loadCouple();
    this.periods.push(new Period('voor', 'Voor Geboorte'));
    this.periods.push(new Period('na', 'Na Geboorte'));
    this.periodFilter = this.periods[0].link;
  }

  setActiveDilemma(dilemma: DilemmaModel) {
    this.activeDilemma = dilemma;
    this.parentAnswer = null;
    this.partnerAnswer = null;

    const possibleAnswers = this.answers.filter(i => i.dilemmaId === dilemma.id);

    const parentResult: ResultModel = this.coupleResults[0].find(function(e: ResultModel) {
      return e.answerId === possibleAnswers[0].id || e.answerId === possibleAnswers[1].id;
    });

    if (parentResult) {
      this.parentAnswer = this.answers.find(i => i.id === parentResult.answerId);
    }

    const partnerResult: ResultModel = this.coupleResults[1].find(function(e: ResultModel) {
      return e.answerId === possibleAnswers[0].id || e.answerId === possibleAnswers[1].id;
    });

    if (parentResult) {
      this.partnerAnswer = this.answers.find(i => i.id === partnerResult.answerId);
    }


  }

  filterDilemmas(period: string) {
    this.periodFilter = period;
    this.filteredDilemmas = this.activeDilemmas.filter(i => i.period === period);
  }

  private loadCouple() {
    this.coupleIsLoading = true;
    this.resultsIsLoading = true;

    this.httpClient.get(this.URL + '/couple/email').pipe(
      map((data) => {
        const parent1$ =  this.httpClient.get(this.URL + '/parent/' + data['parent1Id']);
        const parent2$ =  this.httpClient.get(this.URL + '/parent/' + data['parent2Id']);
        forkJoin([parent1$, parent2$]).subscribe(results => {
          let parent1: ParentModel;
          let parent2: ParentModel;

          if (results[0]['email'] === this.accountModel.email) {
            parent1 = this.buildParentFromResponse(results[0]);
            parent2 = this.buildParentFromResponse(results[1]);
          } else {
            parent1 = this.buildParentFromResponse(results[1]);
            parent2 = this.buildParentFromResponse(results[0]);
          }

          this.couple = new CoupleModel(data['id'], parent1, parent2);
          this.loadResultsData();
        });
      })
    ).subscribe();
  }

  private setActiveDilemmas() {
    for (const d of this.coupleResults[0]) {
      const answer: AnswerModel = this.answers.find(i => i.id === d.answerId);
      const dilemma: DilemmaModel = this.dilemmas.find(i => i.id === answer.dilemmaId);
      this.activeDilemmas.push(dilemma);
    }

    this.filterDilemmas(this.periodFilter);

    if (this.filteredDilemmas.length < 1) {
      this.filterDilemmas('na');
    }
  }

  private loadResultsData() {
    this.resultsIsLoading = true;

    const dilemmas$ = this.getDilemmas$();
    const answers$ = this. getAnswers$();
    const parent1Results$ = this.getResults$(this.couple.parent1.id, 0);
    const parent2Results$ = this.getResults$(this.couple.parent2.id, 1);

    forkJoin([parent1Results$, parent2Results$]).subscribe( () => {
      this.coupleIsLoading = false;
      forkJoin([dilemmas$, answers$]).subscribe( () => {
          this.resultsIsLoading = false;
          this.setActiveDilemmas();
      });
    });
  }

  private getDilemmas$() {
    return this.httpClient.get(this.URL + '/dilemma').pipe(
      map((dilemmas) => {
        for (const item in dilemmas) {
          this.dilemmas.push(this.buildDilemmaFromResponse(dilemmas[item]));
        }
      })
    );
  }

  private getAnswers$() {
    return this.httpClient.get(this.URL + '/answer').pipe(
      map((answers) => {
        for (const item in answers) {
          this.answers.push(this.buildAnswerFromResponse(answers[item]));
        }
      })
    );
  }

  private getResults$(parentId: number, parent: number) {
    return this.httpClient.get(this.URL + '/result/parent/' + parentId.toString()).pipe(
      map((results) => {
        for (const item in results) {
          this.coupleResults[parent].push(this.buildResultFromResponse(results[item]));
        }
      })
    );
  }

  private buildDilemmaFromResponse(response) {
    return new DilemmaModel(
      response['id'],
      response['weekNr'],
      response['theme'],
      response['feedback'],
      response['period']
    );
  }

  private buildAnswerFromResponse(response) {
    return new AnswerModel(
      response['id'],
      response['dilemmaId'],
      response['url'],
      response['text']
    );
  }

  private buildParentFromResponse(response) {
    const parent: ParentModel = new ParentModel(
      response['firstName'],
      response['phoneNr'],
      response['email']
    );
    parent.id = response['id'];
    return parent;
  }

  private buildResultFromResponse(response) {
    return new ResultModel(
      response['id'],
      response['parentId'],
      response['answerId'],
      response['answeredTime'],
      response['sentTime']
    );
  }

}

class Period {
  constructor(public link: string, public name: string) {
  }
}
