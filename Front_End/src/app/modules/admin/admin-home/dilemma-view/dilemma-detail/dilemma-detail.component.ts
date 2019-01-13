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

  answer1Button: string;
  answer2Button: string;

  answer1ButtonText: string;
  answer2ButtonText: string;

  loadedImage: string;

  constructor(private service: DilemmaViewService, private httpClient: HttpClient) {
  }

  ngOnInit() {
    this.answer1Button = 'primary';
    this.answer2Button = 'primary';
    this.answer1ButtonText = 'Nieuw plaatje';
    this.answer2ButtonText = 'Nieuw plaatje';
  }

  newDilemma() {
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
    this.resetFileInput();
  }

  resetFileInput() {
    (<HTMLInputElement>document.getElementById('answer1-file')).value = '';
    (<HTMLInputElement>document.getElementById('answer2-file')).value = '';
    this.answer1Button = 'primary';
    this.answer2Button = 'primary';
    this.answer1ButtonText = 'Nieuw plaatje';
    this.answer2ButtonText = 'Nieuw plaatje';
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

  checkName(event) {
    const file: File = event.target.files[0];
    const acceptedFiles = [
      '.png',
      '.jpg',
      '.jpeg',
    ];
    let matchingName = false;
    acceptedFiles.forEach(extension => {
      if (file.name.endsWith(extension)) {
        matchingName = true;
      }
    });
    return matchingName;
  }

  answer1FileInput(event) {
    const matchingName = this.checkName(event);
    this.answer1Button = (matchingName) ? 'success' : 'danger';
    this.answer1ButtonText = (matchingName) ? event.target.files[0].name : 'Fout bestand';
  }

  answer2FileInput(event) {
    const matchingName = this.checkName(event);
    this.answer2Button = (matchingName) ? 'success' : 'danger';
    this.answer2ButtonText = (matchingName) ? event.target.files[0].name : 'Fout bestand';
  }

  showFirstPic() {
    this.loadedImage = 'https://dubio-groep9.nl/images/' + this.service.answer1.id + this.service.answer1.url;
  }

  showSecondPic() {
    this.loadedImage = 'https://dubio-groep9.nl/images/' + this.service.answer2.id + this.service.answer2.url;
  }

}
