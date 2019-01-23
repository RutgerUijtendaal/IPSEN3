import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {DilemmaService} from './dilemma.service';
import {ActivatedRoute} from '@angular/router';
import {DilemmaAnswerDatabag} from './dilemma-answer.databag';
import {DilemmaLoadingComponent} from './dilemma-loading/dilemma-loading.component';
import {AnswerModel} from '../../../shared/models/answer.model';
import {AnswerVerifyComponent} from './answer-verify/answer-verify.component';

@Component({
  selector: 'app-dilemma-view',
  templateUrl: './dilemma-view.component.html',
  styleUrls: ['./dilemma-view.component.scss'],
})
export class DilemmaViewComponent implements OnInit, AfterViewInit {

  private data: DilemmaAnswerDatabag;
  private choosenAnswer: AnswerModel;

  private token: string;

  @ViewChild(DilemmaLoadingComponent)
  private loadingModal: DilemmaLoadingComponent;

  @ViewChild(AnswerVerifyComponent)
  private answerVerifyModal: AnswerVerifyComponent;

  constructor(private service: DilemmaService, private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.token = this.route.snapshot.params['token'];
  }

  ngAfterViewInit(): void {
    this.service.getDilemmaAnswerDatabag(this.token).subscribe((data: DilemmaAnswerDatabag) => {
      this.data = data;
      this.loadingModal.hide();
    });

    this.loadingModal.show();
  }

  processAnswer(event: { answer: AnswerModel }): void {
    this.choosenAnswer = event.answer;
    this.answerVerifyModal.show();
  }

  submitAnswer(event) {
    this.service.submitDilemmaAnswer(this.token, this.choosenAnswer.id).subscribe(response => {

    }, error => {
      console.log('Something went wrong');
    }, () => {
      console.log('It worked');
    });
  }

}