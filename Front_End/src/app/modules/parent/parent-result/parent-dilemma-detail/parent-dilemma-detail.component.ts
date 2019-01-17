import { Component, OnInit } from '@angular/core';
import {ParentService} from '../../parent.service';

@Component({
  selector: 'app-parent-dilemma-detail',
  templateUrl: './parent-dilemma-detail.component.html',
  styleUrls: ['./parent-dilemma-detail.component.scss']
})
export class ParentDilemmaDetailComponent implements OnInit {

  parentService: ParentService;

  constructor(parentService: ParentService) {
    this.parentService = parentService;
  }

  ngOnInit() {
  }

}
