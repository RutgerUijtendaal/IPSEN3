import {Component, Input, OnInit} from '@angular/core';
import {DilemmaModel} from '../../../../../shared/models/dilemma.model';
import {AnswerModel} from '../../../../../shared/models/answer.model';
import {DilemmaViewService} from '../dilemma-view-service';
import {AppComponent} from '../../../../../app.component';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {DilemmaViewHttpService} from '../dilemma-view-http.service';
import { AuthenticationService } from '../../../../../core/auth/authentication.service';

@Component({
  selector: 'app-dilemma-detail',
  templateUrl: './dilemma-detail.component.html',
  styleUrls: ['./dilemma-detail.component.scss']
})
export class DilemmaDetailComponent implements OnInit {

  editedTheme: string;
  editedFeedback: string;
  editedAnswerText1: string;
  editedAnswerText2: string;

  answer1Button: string;
  answer2Button: string;

  saveButtonClass: string;
  saveButtonText: string;

  answer1ButtonText: string;
  answer2ButtonText: string;

  answer1FileGood: boolean;
  answer2FileGood: boolean;

  answerFile1: File;
  answerFile2: File;

  loadedImage: string;

  service: DilemmaViewService;
  canEdit: any;

  constructor(service: DilemmaViewService,
              private httpService: DilemmaViewHttpService,
              authenticationService: AuthenticationService) {
    this.service = service;
    this.service.click.subscribe(value => {
      this.resetFileInput();
    });
    this.resetSaveButton();
    this.answer1FileGood = true;
    this.answer2FileGood = true;
    this.loadedImage = '';
    this.canEdit = authenticationService.accountModel.right > 1;
  }

  ngOnInit() {
    this.resetFileInput();
  }

  resetSaveButton() {
    this.saveButtonClass = 'primary';
    this.saveButtonText = 'OPSLAAN';
  }

  getDetails() {
    this.editedTheme = (<HTMLInputElement>document.getElementsByClassName('dilemma-theme')[0]).value;
    this.editedFeedback = (<HTMLInputElement>document.getElementsByClassName('dilemma-feedback')[0]).value;
    this.editedAnswerText1 = (<HTMLInputElement>document.getElementsByClassName('answer1-text')[0]).value;
    this.editedAnswerText2 = (<HTMLInputElement>document.getElementsByClassName('answer2-text')[0]).value;
  }

  resetDetails() {
    (<HTMLInputElement>document.getElementsByClassName('dilemma-theme')[0]).value = this.service.dilemma.theme;
    (<HTMLInputElement>document.getElementsByClassName('dilemma-feedback')[0]).value = String(this.service.dilemma.feedback);
    (<HTMLInputElement>document.getElementsByClassName('answer1-text')[0]).value = this.service.answer1.text;
    (<HTMLInputElement>document.getElementsByClassName('answer2-text')[0]).value = this.service.answer2.text;
    this.resetFileInput();
  }

  resetFileInput() {
    this.answer1Button = 'primary';
    this.answer2Button = 'primary';
    this.answer1ButtonText = 'Nieuw plaatje';
    this.answer2ButtonText = 'Nieuw plaatje';
    this.answer1FileGood = true;
    this.answer2FileGood = true;
    this.answerFile1 = null;
    this.answerFile2 = null;
    const inputElement = (<HTMLInputElement>document.getElementById('answer1-file'));
    if (inputElement != null) {
      inputElement.value = null;
      (<HTMLInputElement>document.getElementById('answer2-file')).value = null;
    }
  }

  verifyFields() {
    this.getDetails();
    if (this.editedTheme.length === 0 ||
      this.editedFeedback.length === 0 ||
      !this.answer1FileGood || !this.answer2FileGood) {
      return false;
    }
    return true;
  }

  saveRequest() {
    if (!this.verifyFields()) {
      this.saveButtonText = 'Foute velden';
      this.saveButtonClass = 'danger';
      setTimeout(() => {
        this.resetSaveButton();
      }, 1500);
      return;
    }

    let goodSave = false;
    if (this.newDilemma()) {
      goodSave = this.saveDilemma();
    } else {
      goodSave = this.updateDilemma();
    }
    if (goodSave) {
      this.saveButtonText = 'OPGESLAGEN';
      this.saveButtonClass = 'success';
      setTimeout(() => {
        this.resetSaveButton();
      }, 1500);

    }
  }

  newDilemma() {
    if (this.service.dilemma.id === -1) {
      return true;
    }
    return false;
  }

  saveDilemma() {
    this.getDetails();
    const currentDilemma = this.service.dilemma;
    const currentAnswer1 = this.service.answer1;
    const currentAnswer2 = this.service.answer2;
    currentDilemma.theme = this.editedTheme;
    currentDilemma.feedback = this.editedFeedback;
    currentAnswer1.text = this.editedAnswerText1;
    currentAnswer2.text = this.editedAnswerText2;
    this.httpService.saveDilemma(currentDilemma, currentAnswer1, currentAnswer2);
    this.uploadPictures();
    return true;
  }

  uploadPictures() {
    if (this.answer1FileGood && this.answerFile1 != null) {
      this.service.answer1.extension = '.' + this.answerFile1.name.split('.').pop();
      this.httpService.uploadPicture(this.answerFile1, this.service.answer1.id + this.service.answer1.extension);
    }
    if (this.answer2FileGood && this.answerFile2 != null) {
      this.service.answer2.extension = '.' + this.answerFile2.name.split('.').pop();
      this.httpService.uploadPicture(this.answerFile2, this.service.answer2.id + this.service.answer2.extension);
    }
    this.resetFileInput();
  }

  updateDilemma() {
    this.getDetails();
    const currentDilemma = this.service.dilemma;
    const currentAnswer1 = this.service.answer1;
    const currentAnswer2 = this.service.answer2;
    currentDilemma.theme = this.editedTheme;
    currentDilemma.feedback = this.editedFeedback;
    currentAnswer1.text = this.editedAnswerText1;
    currentAnswer2.text = this.editedAnswerText2;
    this.uploadPictures();
    this.httpService.updateDilemmaAnswers(currentDilemma.id, currentDilemma,
      currentAnswer1.id, currentAnswer1,
      currentAnswer2.id, currentAnswer2);
    return true;
  }

  deleteDilemma() {
    this.httpService.deleteDilemma(this.service.dilemma.id);
    this.service.dilemma = null;
    this.service.answer1 = null;
    this.service.answer2 = null;
    this.service.delete.next(this.service.dilemma);
    this.resetFileInput();
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
    this.answer1FileGood = matchingName;
    this.answer1ButtonText = (matchingName) ? event.target.files[0].name : 'Fout bestand';
    if (matchingName) {
      this.answerFile1 = event.target.files[0];
    }
  }

  answer2FileInput(event) {
    const matchingName = this.checkName(event);
    this.answer2Button = (matchingName) ? 'success' : 'danger';
    this.answer2FileGood = matchingName;
    this.answer2ButtonText = (matchingName) ? event.target.files[0].name : 'Fout bestand';
    if (matchingName) {
      this.answerFile2 = event.target.files[0];
    }
  }

  showFirstPic() {
    this.loadedImage = 'https://dubio-groep9.nl/.answer-images/' + this.service.answer1.id + this.service.answer1.extension;
  }

  showSecondPic() {
    this.loadedImage = 'https://dubio-groep9.nl/.answer-images/' + this.service.answer2.id + this.service.answer2.extension;
  }

}
