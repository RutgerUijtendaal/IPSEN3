import { Component, OnInit } from '@angular/core';
import {ParentDilemmaListService} from '../parent-dilemma-list.service';

@Component({
  selector: 'app-parent-dilemma-list',
  templateUrl: './parent-dilemma-list.component.html',
  styleUrls: ['./parent-dilemma-list.component.scss']
})
export class ParentDilemmaListComponent implements OnInit {

  parentService: ParentDilemmaListService;

  constructor(parentService: ParentDilemmaListService) {
    this.parentService = parentService;
  }

  ngOnInit() {
  }

  onPeriodChange(period) {
    this.parentService.filterDilemmas(period);
  }

}

