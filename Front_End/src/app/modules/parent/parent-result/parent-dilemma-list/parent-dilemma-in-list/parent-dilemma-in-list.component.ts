import {Component, Input, OnInit} from '@angular/core';
import {DilemmaModel} from '../../../../../shared/models/dilemma.model';

@Component({
  selector: 'app-parent-dilemma-in-list',
  templateUrl: './parent-dilemma-in-list.component.html',
  styleUrls: ['./parent-dilemma-in-list.component.scss']
})
export class ParentDilemmaInListComponent implements OnInit {

  @Input() dilemma: DilemmaModel;

  constructor() { }

  ngOnInit() {
  }

}
