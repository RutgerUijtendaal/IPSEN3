import { Component, OnInit } from '@angular/core';
import {ParentDataService} from '../parent-data.service';

@Component({
  selector: 'app-parent-home',
  templateUrl: './parent-home.component.html',
  styleUrls: ['./parent-home.component.scss']
})
export class ParentHomeComponent implements OnInit {

  constructor(private parentService: ParentDataService) {

  }

  ngOnInit() {
  }


}
