import { Component, OnInit } from '@angular/core';
import {AdminViewService} from '../admin-view.service';

@Component({
  selector: 'app-admin-detail',
  templateUrl: './admin-detail.component.html',
  styleUrls: ['./admin-detail.component.scss']
})
export class AdminDetailComponent implements OnInit {

  viewService: AdminViewService;

  constructor(viewService: AdminViewService) {
    this.viewService = viewService;
  }

  ngOnInit() {
  }

}
