import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { DilemmaModel } from '../../../../../../shared/models/dilemma.model';

@Component({
  selector: 'app-dilemma-in-list',
  templateUrl: './dilemma-in-list.component.html',
  styleUrls: ['./dilemma-in-list.component.scss']
})
export class DilemmaInListComponent implements OnInit {

  @Input() dilemma: DilemmaModel;

  constructor() {
  }

  ngOnInit() {
  }

}
