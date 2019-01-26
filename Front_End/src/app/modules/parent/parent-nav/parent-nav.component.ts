import { Component, OnInit } from '@angular/core';
import {ParentDataService} from '../parent-data.service';

@Component({
  selector: 'app-parent-nav',
  templateUrl: './parent-nav.component.html',
  styleUrls: ['./parent-nav.component.scss']
})
export class ParentNavComponent implements OnInit {

  parentService: ParentDataService;

  constructor(parentService: ParentDataService) {
    this.parentService = parentService;
  }

  ngOnInit() {
    this.parentService.getData();
  }

}
