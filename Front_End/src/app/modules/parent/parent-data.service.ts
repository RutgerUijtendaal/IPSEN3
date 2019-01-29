import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {AuthenticationService} from '../../core/auth/authentication.service';
import {AccountModel} from '../../shared/models/account.model';
import {CoupleModel} from '../../shared/models/couple.model';
import {AppComponent} from '../../app.component';
import {map} from 'rxjs/operators';
import {forkJoin} from 'rxjs';
import {ParentModel} from '../../shared/models/parent.model';
import {ResultModel} from '../../shared/models/result.model';
import {AnswerModel} from '../../shared/models/answer.model';
import {DilemmaModel} from '../../shared/models/dilemma.model';
import {ChildModel} from '../../shared/models/child.model';

@Injectable()
export class ParentDataService {

  private URL = AppComponent.environment.server;

  accountModel: AccountModel;

  couple: CoupleModel;
  child: ChildModel;
  coupleResults: [ResultModel[], ResultModel[]] = [[], []];
  dilemmas: DilemmaModel[] = [];
  answers: AnswerModel[] = [];
  activeDilemmas: DilemmaModel[] = [];

  isLoading = false;

  constructor(private authenticationService: AuthenticationService,
              private httpClient: HttpClient) {
    this.accountModel = authenticationService.accountModel;
  }

  public getData() {
    this.isLoading = true;
    this.clearData();
    this.getCouple$().subscribe();
  }

  private clearData() {
    this.coupleResults = [[], []];
    this.dilemmas = [];
    this.answers = [];
    this.activeDilemmas = [];
  }

  private setActiveDilemmas() {
    this.coupleResults[0].forEach( (item, index) => {
      if (item.answerId !== 0 && this.coupleResults[1][index].answerId !== 0) {
        const answer: AnswerModel = this.answers.find(i => i.id === item.answerId);
        const dilemma: DilemmaModel = this.dilemmas.find(i => i.id === answer.dilemmaId);
        this.activeDilemmas.push(dilemma);
      }
    });
  }

  private loadData() {
    const dilemmas$ = this.getDilemmas$();
    const answers$ = this.getAnswers$();
    const getChild$ = this.getChild$();
    const parent1Results$ = this.getResults$(this.couple.parent1.id, 0);
    const parent2Results$ = this.getResults$(this.couple.parent2.id, 1);

    forkJoin([parent1Results$, parent2Results$, getChild$, dilemmas$, answers$]).subscribe( () => {
      this.setActiveDilemmas();
      this.isLoading = false;
    });
  }

  private getCouple$() {
    return this.httpClient.get(this.URL + '/couple/email').pipe(
      map((data) => {
        const parent1$ =  this.httpClient.get<ParentModel>(this.URL + '/parent/' + data['parent1Id']);
        const parent2$ =  this.httpClient.get<ParentModel>(this.URL + '/parent/' + data['parent2Id']);
        forkJoin([parent1$, parent2$]).subscribe(results => {
          let parent1: ParentModel;
          let parent2: ParentModel;

          if (results[0]['email'] === this.accountModel.email) {
            parent1 = results[0];
            parent2 = results[1];
          } else {
            parent1 = results[1];
            parent2 = results[0];
          }

          this.couple = new CoupleModel(data['id'], parent1, parent2, data['date'], data['token']);
          this.loadData();
        });
      })
    );
  }

  private getChild$() {
    return this.httpClient.get<ChildModel>(this.URL + '/child/couple/' + this.couple.coupleId).pipe(
      map((child: ChildModel) => {
        this.child = child;
      })
    );
  }

  private getDilemmas$() {
    return this.httpClient.get<DilemmaModel[]>(this.URL + '/dilemma').pipe(
      map((dilemmas: DilemmaModel[]) => {
        dilemmas.forEach(dilemma => {
          this.dilemmas.push(dilemma);
        });
      })
    );
  }

  private getAnswers$() {
    return this.httpClient.get<AnswerModel[]>(this.URL + '/answer').pipe(
      map((answers: AnswerModel[]) => {
        answers.forEach(answers1 => {
          this.answers.push(answers1);
        });
      })
    );
  }

  private getResults$(parentId: number, parent: number) {
    return this.httpClient.get<ResultModel[]>(this.URL + '/result/parent/' + parentId.toString()).pipe(
      map((results: ResultModel[]) => {
        if (results && results.length > 0) {
          results.forEach(result => {
            this.coupleResults[parent].push(result);

          });
        }
      })
    );
  }

}

