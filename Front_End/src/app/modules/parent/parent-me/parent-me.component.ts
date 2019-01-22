import { Component, OnInit } from '@angular/core';
import {ParentService} from '../parent.service';

@Component({
  selector: 'app-parent-me',
  templateUrl: './parent-me.component.html',
  styleUrls: ['./parent-me.component.scss']
})
export class ParentMeComponent implements OnInit {

  parentService: ParentService;

  constructor(parentService: ParentService) {
    this.parentService = parentService;
  }

  ngOnInit() {
  }

}