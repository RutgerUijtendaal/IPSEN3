import { Component, OnInit } from '@angular/core';
import {AdminViewService} from '../admin-view.service';

@Component({
  selector: 'app-admin-detail',
  templateUrl: './admin-detail.component.html',
  styleUrls: ['./admin-detail.component.scss']
})
export class AdminDetailComponent implements OnInit {

  viewService: AdminViewService;

  saveButtonClass: string;
  saveButtonText: string;

  resetSaveButton() {
    this.saveButtonClass = 'primary';
    this.saveButtonText = 'OPSLAAN';
  }

  constructor(viewService: AdminViewService) {
    this.viewService = viewService;
  }

  deleteAdmin() {

  }

  ngOnInit() {
    this.resetSaveButton();
  }

}
