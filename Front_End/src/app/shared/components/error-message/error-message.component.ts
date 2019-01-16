import {Component, Input, OnInit} from '@angular/core';
import {animate, state, style, transition, trigger} from '@angular/animations';

@Component({
  selector: 'app-widget-error-message',
  templateUrl: './error-message.component.html',
  styleUrls: ['./error-message.component.scss'],
  animations: [
    trigger('enterLeave', [
      transition(':enter', [
        style({ opacity: 0}),
        animate(400,
          style({ opacity: 1}),
        )
      ]),
      transition(':leave', [
        style({ opacity: 1}),
        animate(400,
          style({ opacity: 0}),
        )
      ])
    ])
  ]
})
export class ErrorMessageComponent implements OnInit {

  @Input()
  errorMessage: string;

  constructor() { }

  ngOnInit() {
  }

}
