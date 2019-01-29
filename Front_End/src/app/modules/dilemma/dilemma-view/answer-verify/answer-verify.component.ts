import {AfterViewInit, Component, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {ModalDirective} from 'angular-bootstrap-md';
import {AnswerModel} from '../../../../shared/models/answer.model';

@Component({
  selector: 'app-answer-verify',
  templateUrl: './answer-verify.component.html',
  styleUrls: ['./answer-verify.component.scss']
})
export class AnswerVerifyComponent implements OnInit {

  @ViewChild('modal')
  private modal: ModalDirective;

  @Input()
  public answer: AnswerModel;

  @Output()
  private verify: EventEmitter<void> = new EventEmitter<void>();

  constructor() { }

  ngOnInit() {
  }

  hide() {
    this.modal.hide();
  }

  show() {
    this.modal.show();
  }

  verifyAnswer() {
    this.modal.hide();
    this.verify.emit();
  }

}
