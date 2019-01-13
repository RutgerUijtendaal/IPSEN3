import {Component, Input, OnInit} from '@angular/core';
import {DilemmaModel} from '../../../../../shared/models/dilemma.model';
import {AnswerModel} from '../../../../../shared/models/answer.model';
import {DilemmaViewService} from '../dilemma-view-service';
import {AppComponent} from '../../../../../app.component';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-dilemma-detail',
  templateUrl: './dilemma-detail.component.html',
  styleUrls: ['./dilemma-detail.component.scss']
})
export class DilemmaDetailComponent implements OnInit {

  URL = AppComponent.environment.server;

  editedTheme: string;
  editedWeekNr: string;
  editedFeedback: string;
  editedAnswerText1: string;
  editedAnswerText2: string;

  loadedImage: string;

  constructor(private service: DilemmaViewService, private httpClient: HttpClient) {
  }

  ngOnInit() {
  }

  getDetails() {
    this.editedTheme = (<HTMLInputElement>document.getElementsByClassName('dilemma-theme')[0]).value;
    this.editedWeekNr = (<HTMLInputElement>document.getElementsByClassName('dilemma-weeknr')[0]).value;
    this.editedFeedback = (<HTMLInputElement>document.getElementsByClassName('dilemma-feedback')[0]).value;
    this.editedAnswerText1 = (<HTMLInputElement>document.getElementsByClassName('answer1-text')[0]).value;
    this.editedAnswerText2 = (<HTMLInputElement>document.getElementsByClassName('answer2-text')[0]).value;
  }

  resetDetails() {
    (<HTMLInputElement>document.getElementsByClassName('dilemma-theme')[0]).value = this.service.dilemma.theme;
    (<HTMLInputElement>document.getElementsByClassName('dilemma-weeknr')[0]).value = String(this.service.dilemma.weekNr);
    (<HTMLInputElement>document.getElementsByClassName('dilemma-feedback')[0]).value = String(this.service.dilemma.feedback);
    (<HTMLInputElement>document.getElementsByClassName('answer1-text')[0]).value = this.service.answer1.text;
    (<HTMLInputElement>document.getElementsByClassName('answer2-text')[0]).value = this.service.answer2.text;
  }

  saveDilemma() {
    this.getDetails();
    const currentDilemma = this.service.dilemma;
    const currentAnswer1 = this.service.answer1;
    const currentAnswer2 = this.service.answer2;
    currentDilemma.theme = this.editedTheme;
    currentDilemma.feedback = this.editedFeedback;
    currentDilemma.weekNr = Number(this.editedWeekNr);
    currentAnswer1.text = this.editedAnswerText1;
    currentAnswer2.text = this.editedAnswerText2;
    console.log(this.URL);
    this.httpClient.put(this.URL + '/dilemma/' + currentDilemma.id, currentDilemma).subscribe(res => {
      console.log(res.toString());
    });
    this.httpClient.put(this.URL + '/answer/' + currentAnswer1.id, currentAnswer1).subscribe(res => {
      console.log(res.toString());
    });
    this.httpClient.put(this.URL + '/answer/' + currentAnswer2.id, currentAnswer2).subscribe(res => {
      console.log(res.toString());
    });
  }

  deleteDilemma() {
    this.service.dilemma = null;
    this.service.answer1 = null;
    this.service.answer2 = null;
    this.service.delete.next(this.service.dilemma);
  }

  showFirstPic() {
    this.loadedImage = 'https://dubio-groep9.nl/images/' + this.service.answer1.url;
  }

  showSecondPic() {
    this.loadedImage = 'https://dubio-groep9.nl/images/' + this.service.answer2.url;
  }

}
