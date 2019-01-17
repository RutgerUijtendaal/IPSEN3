import { Component, OnInit } from '@angular/core';
import {ParentService} from '../../parent.service';

@Component({
  selector: 'app-parent-dilemma-list',
  templateUrl: './parent-dilemma-list.component.html',
  styleUrls: ['./parent-dilemma-list.component.scss']
})
export class ParentDilemmaListComponent implements OnInit {

  constructor(private parentService: ParentService) {
  }

  ngOnInit() {
  }

  onPeriodeChange(periode) {
    this.parentService.filterDilemmas(periode);
  }

}

