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
import {ChildModel} from "../../shared/models/child.model";

@Injectable()
export class ParentService {

  private URL = AppComponent.environment.server;

  accountModel: AccountModel;

  couple: CoupleModel;
  child: ChildModel;
  coupleResults: [ResultModel[], ResultModel[]] = [[], []];
  dilemmas: DilemmaModel[] = [];
  answers: AnswerModel[] = [];
  activeDilemmas: DilemmaModel[] = [];

  coupleIsLoading = false;
  resultsIsLoading = false;

  constructor(private authenticationService: AuthenticationService,
              private httpClient: HttpClient) {
    this.accountModel = authenticationService.accountModel;
    this.loadCouple();
  }

  private loadCouple() {
    this.coupleIsLoading = true;
    this.resultsIsLoading = true;

    this.getCouple$().subscribe();
  }

  private setActiveDilemmas() {
    this.coupleResults[0].forEach( (item, index) => {
      if (item.answerId !== 0 && this.coupleResults[1][index].answerId !== 0) {
        const answer: AnswerModel = this.answers.find(i => i.id === item.answerId);
        const dilemma: DilemmaModel = this.dilemmas.find(i => i.id === answer.dilemmaId);
        this.activeDilemmas.push(dilemma);
      }
    })
  }

  private loadData() {
    this.resultsIsLoading = true;

    const dilemmas$ = this.getDilemmas$();
    const answers$ = this.getAnswers$();
    const getChild$ = this.getChild$();
    const parent1Results$ = this.getResults$(this.couple.parent1.id, 0);
    const parent2Results$ = this.getResults$(this.couple.parent2.id, 1);

    forkJoin([parent1Results$, parent2Results$, getChild$]).subscribe( () => {
      this.coupleIsLoading = false;
      forkJoin([dilemmas$, answers$]).subscribe( () => {
          this.resultsIsLoading = false;
          this.setActiveDilemmas();
      });
    });
  }

  private getCouple$() {
    return this.httpClient.get(this.URL + '/couple/email').pipe(
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
          this.loadData();
        });
      })
    )
  }

  private getChild$() {
    return this.httpClient.get<ChildModel>(this.URL + '/child/couple/' + this.couple.coupleId).pipe(
      map((child: ChildModel) => {
        this.child = child;
      })
    )
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

