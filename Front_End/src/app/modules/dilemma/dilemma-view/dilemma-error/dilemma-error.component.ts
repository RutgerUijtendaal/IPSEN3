import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-dilemma-error',
  templateUrl: './dilemma-error.component.html',
  styleUrls: ['./dilemma-error.component.scss']
})
export class DilemmaErrorComponent implements OnInit {
  @Input() errorMessage: string;

  constructor() { }

  ngOnInit() {
  }

}
