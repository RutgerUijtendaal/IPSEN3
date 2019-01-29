import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {AnswerModel} from '../../../../shared/models/answer.model';

@Component({
  selector: 'app-dilemma-answer',
  templateUrl: './dilemma-answer.component.html',
  styleUrls: ['./dilemma-answer.component.scss']
})
export class DilemmaAnswerComponent implements OnInit {

  @Input()
  private answer: AnswerModel;

  @Output()
  private select: EventEmitter<{answer: AnswerModel}> = new EventEmitter<{answer: AnswerModel}>();

  constructor() {
  }

  ngOnInit() {
  }

  selectAnswer() {
    this.select.emit({answer: this.answer});
  }

}
