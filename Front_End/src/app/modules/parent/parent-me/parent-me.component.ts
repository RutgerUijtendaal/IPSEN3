import { Component, OnInit } from '@angular/core';
import {ParentDataService} from '../parent-data.service';

@Component({
  selector: 'app-parent-me',
  templateUrl: './parent-me.component.html',
  styleUrls: ['./parent-me.component.scss']
})
export class ParentMeComponent implements OnInit {

  parentService: ParentDataService;

  constructor(parentService: ParentDataService) {
    this.parentService = parentService;
  }

  ngOnInit() {
  }


}
