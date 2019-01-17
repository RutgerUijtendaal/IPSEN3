import { Component, OnInit } from '@angular/core';
import {ParentService} from '../../parent.service';

@Component({
  selector: 'app-parent-dilemma-list',
  templateUrl: './parent-dilemma-list.component.html',
  styleUrls: ['./parent-dilemma-list.component.scss']
})
export class ParentDilemmaListComponent implements OnInit {

  parentService: ParentService;

  constructor(parentService: ParentService) {
    this.parentService = parentService;
  }

  ngOnInit() {
  }

  onPeriodChange(period) {
    this.parentService.filterDilemmas(period);
  }

}

