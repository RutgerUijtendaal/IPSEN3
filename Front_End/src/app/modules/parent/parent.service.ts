import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {AuthenticationService} from '../../core/auth/authentication.service';
import {AccountModel} from '../../shared/models/account.model';
import {CoupleModel} from '../../shared/models/couple.model';
import {AppComponent} from '../../app.component';
import {map, mergeMap} from 'rxjs/operators';
import {forkJoin} from 'rxjs';
import {ParentModel} from '../../shared/models/parent.model';

@Injectable()
export class ParentService {

  URL = AppComponent.environment.server;

  accountModel: AccountModel;
  couple: CoupleModel;

  constructor(private authenticationService: AuthenticationService,
              private httpClient: HttpClient) {
    this.accountModel = authenticationService.accountModel;
  }

  getCouple() {
    this.httpClient.get(this.URL + '/couple/email').pipe(
      map((data) => {
        let parent1$ =  this.httpClient.get(this.URL + '/parent/' + data['parent1Id']);
        let parent2$ =  this.httpClient.get(this.URL + '/parent/' + data['parent2Id']);
        forkJoin([parent1$, parent2$]).subscribe(results => {

          let parent1: ParentModel;
          let parent2: ParentModel;

          if(results[0]['email'] === this.accountModel.email) {
            parent1 = this.buildParentFromResult(results[0]);
            parent2 = this.buildParentFromResult(results[1]);
          } else {
            parent1 = this.buildParentFromResult(results[1]);
            parent2 = this.buildParentFromResult(results[0]);
          }

          this.couple = new CoupleModel(data['id'], parent1, parent2)
          console.log(this.couple)
        })
      })
    ).subscribe();

  }

  private buildParentFromResult(result) {
    let parent: ParentModel = new ParentModel(result['firstName'], result['phoneNr'], result['email'])
    parent.id = result['id'];
    return parent;
  }


}
