import { Component, OnInit } from '@angular/core';
import {ParentDataService} from '../parent-data.service';

@Component({
  selector: 'app-parent-result',
  templateUrl: './parent-result.component.html',
  styleUrls: ['./parent-result.component.scss']
})
export class ParentResultComponent implements OnInit {

  parentService: ParentDataService;

  constructor(parentService: ParentDataService) {
    this.parentService = parentService;
  }

  ngOnInit() {
  }

}
