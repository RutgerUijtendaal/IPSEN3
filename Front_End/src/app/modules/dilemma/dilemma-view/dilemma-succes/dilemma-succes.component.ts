import {Component, Input, OnInit} from '@angular/core';
import {DilemmaService} from '../dilemma.service';
import {DilemmaModel} from '../../../../shared/models/dilemma.model';
import {map} from 'rxjs/operators';

@Component({
  selector: 'app-dilemma-succes',
  templateUrl: './dilemma-succes.component.html',
  styleUrls: ['./dilemma-succes.component.scss']
})
export class DilemmaSuccesComponent implements OnInit {

  @Input() dilemma: DilemmaModel;
  private feedbackSend = false;

  constructor(private dilemmaService: DilemmaService) { }

  ngOnInit() {

  }

  sendFeedback(rating: number) {
    this.dilemmaService.submitDilemmaFeedback(rating, this.dilemma).pipe(
      map((response) => {
        this.feedbackSend = true;
      })
    ).subscribe();
  }

}
