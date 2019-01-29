import { Component, OnInit } from '@angular/core';
import {ParentDataService} from '../../parent-data.service';

@Component({
  selector: 'app-parent-dilemma-new',
  templateUrl: './parent-dilemma-new.component.html',
  styleUrls: ['./parent-dilemma-new.component.scss']
})
export class ParentDilemmaNewComponent implements OnInit {

  parentService: ParentDataService;

  constructor(parentService: ParentDataService) {
    this.parentService = parentService;
  }

  ngOnInit() {
  }


}
