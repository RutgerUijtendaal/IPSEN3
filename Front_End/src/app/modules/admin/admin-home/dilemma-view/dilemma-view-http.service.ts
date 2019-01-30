import {Injectable} from '@angular/core';
import {Subject} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {AppComponent} from '../../../../app.component';
import {DilemmaModel} from '../../../../shared/models/dilemma.model';
import {AnswerModel} from '../../../../shared/models/answer.model';

@Injectable()
export class DilemmaViewHttpService {

  private URL = AppComponent.environment.server;

  // success: Subject<string>;
  // failure: Subject<string>;

  loadedData: Subject<any>;

  constructor(private httpClient: HttpClient) {
    // this.success = new Subject();
    // this.failure = new Subject();
    this.loadedData = new Subject();
    this.loadedData.asObservable();
    // this.success.asObservable();
    // this.failure.asObservable();
  }

  loadDilemmas(period: string) {
    const getUrl = this.URL + '/dilemma/' + period;
    const dilemmas: DilemmaModel[] = [];
    this.httpClient.get(getUrl).subscribe(data => {
      (data as DilemmaModel[]).forEach(dilemma => dilemmas.push(dilemma));
      this.loadedData.next(0);
    });
    return dilemmas;
  }

  loadAnswers() {
    const getUrl = this.URL + '/answer';
    const answers: AnswerModel[] = [];
    this.httpClient.get(getUrl).subscribe(data => {
      (data as AnswerModel[]).forEach(answer => answers.push(answer));
      this.loadedData.next(0);
    });
    return answers;
  }

  updateDilemma(dilemma: DilemmaModel) {
    this.httpClient.put(this.URL + '/dilemma/' + dilemma.id, dilemma).subscribe(res => {
    });
  }

  saveDilemma(dilemma: DilemmaModel, answer1: AnswerModel, answer2: AnswerModel) {
    this.httpClient.post(this.URL + '/dilemma', dilemma).subscribe(dilemmaId => {
      dilemma.id = Number(dilemmaId);
      answer1.dilemmaId = Number(dilemmaId);
      answer2.dilemmaId = Number(dilemmaId);
      this.httpClient.post(this.URL + '/answer', answer1).subscribe(ans1 => {
        answer1.id = Number(ans1);
        this.httpClient.post(this.URL + '/answer', answer2).subscribe(ans2 => {
          answer2.id = Number(ans2);
        });
      });
    });
  }

  uploadPicture(image: File, filename: string) {
    const formData = new FormData();
    formData.append('file', image);
    formData.append('filename', filename);
    this.httpClient.post(this.URL + '/imageupload', formData).subscribe(res => {
    });
  }

  deleteDilemma(id: number) {
    this.httpClient.delete(this.URL + /dilemma/ + id).subscribe(res => {
    });
  }

  updateDilemmaAnswers(dilemmaId: number, dilemma: DilemmaModel,
                answerId1: number, answer1: AnswerModel,
                answerId2: number, answer2: AnswerModel) {
    this.httpClient.put(this.URL + '/dilemma/' + dilemmaId, dilemma).subscribe(res => {
    });
    this.httpClient.put(this.URL + '/answer/' + answerId1, answer1).subscribe(res => {
    });
    this.httpClient.put(this.URL + '/answer/' + answerId2, answer2).subscribe(res => {
    });

  }
}
