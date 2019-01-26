import { Component, OnInit } from '@angular/core';
import {ParentService} from '../parent.service';

@Component({
  selector: 'app-parent-nav',
  templateUrl: './parent-nav.component.html',
  styleUrls: ['./parent-nav.component.scss']
})
export class ParentNavComponent implements OnInit {

  parentService: ParentService;

  constructor(parentService: ParentService) {
    this.parentService = parentService;
  }

  ngOnInit() {
    this.parentService.getData();
  }

}
