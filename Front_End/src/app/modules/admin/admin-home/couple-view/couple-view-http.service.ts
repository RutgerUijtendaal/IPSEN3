import {Injectable} from '@angular/core';
import {Subject} from 'rxjs';
import {CoupleModel} from '../../../../shared/models/couple.model';
import {HttpClient} from '@angular/common/http';
import {AppComponent} from '../../../../app.component';

@Injectable()
export class CoupleViewHttpService {

  private URL = AppComponent.environment.server;

  success: Subject<any>;
  // failure: Subject<string>;

  constructor(private httpClient: HttpClient) {
    this.success = new Subject();
    // this.failure = new Subject();
    this.success.asObservable();
    // this.failure.asObservable();
  }

  loadCouples() {
    const couples: CoupleModel[] = [];
    this.httpClient.get(this.URL + '/couple-list').subscribe(data => {
      (data as CoupleModel[]).forEach(couple => couples.push(couple));
      this.success.next(0);
    });
    return couples;
  }

  deleteCouple(couple: CoupleModel) {
    this.httpClient.delete(this.URL + '/couple/' + couple.coupleId).subscribe((res) => {
    });
  }

}
