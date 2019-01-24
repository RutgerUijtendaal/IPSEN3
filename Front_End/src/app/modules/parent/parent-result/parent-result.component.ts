import { Component, OnInit } from '@angular/core';
import {ParentService} from '../parent.service';

@Component({
  selector: 'app-parent-result',
  templateUrl: './parent-result.component.html',
  styleUrls: ['./parent-result.component.scss']
})
export class ParentResultComponent implements OnInit {

  parentService: ParentService;

  constructor(parentService: ParentService) {
    this.parentService = parentService;
  }

  ngOnInit() {
  }

}
