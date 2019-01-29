import { Component, OnInit } from '@angular/core';
import {ParentDataService} from '../../parent-data.service';
import { ParentDilemmaListService } from '../parent-dilemma-list.service';

@Component({
  selector: 'app-parent-dilemma-detail',
  templateUrl: './parent-dilemma-detail.component.html',
  styleUrls: ['./parent-dilemma-detail.component.scss']
})
export class ParentDilemmaDetailComponent implements OnInit {

  constructor(public parentService: ParentDataService,
              public parentDilemmaService: ParentDilemmaListService) {
  }

  ngOnInit() {
  }

}
