import { Component, OnInit } from '@angular/core';
import {AdminViewService} from '../admin-view.service';
import {HttpClient} from '@angular/common/http';
import {AppComponent} from '../../../../../app.component';

@Component({
  selector: 'app-admin-detail',
  templateUrl: './admin-detail.component.html',
  styleUrls: ['./admin-detail.component.scss']
})
export class AdminDetailComponent implements OnInit {

  URL = AppComponent.environment.server;

  viewService: AdminViewService;

  saveButtonClass: string;
  saveButtonText: string;

  rightsText: string;

  studentText = 'Een student mag alleen statistieken en dilemma\'s bekijken.';
  medewerkerText = 'Een medewerker mag meer dan een student.';
  beheerderText = 'Een beheerder mag alles.';

  resetSaveButton() {
    this.saveButtonClass = 'primary';
    this.saveButtonText = 'OPSLAAN';
  }

  constructor(viewService: AdminViewService, private httpClient: HttpClient) {
    this.viewService = viewService;
    this.viewService.adminClicked.subscribe(val => this.updateRadioButtons());
  }

  deleteAdmin() {
    this.httpClient.delete(this.URL + /admin/ + this.viewService.admin.id).subscribe(res => {
    });
    this.viewService.delete.next(this.viewService.admin);
    this.viewService.admin = null;
  }

  saveRequest() {

  }

  updateRadioButtons() {
    setTimeout(() => {
      let radiobtn;
      switch (Number(this.viewService.admin.rightId)) {
        case 1:
          radiobtn = document.getElementById('student');
          this.rightsText = this.studentText;
          break;
        case 2:
          radiobtn = document.getElementById('medewerker');
          this.rightsText = this.medewerkerText;
          break;
        case 3:
          radiobtn = document.getElementById('beheerder');
          this.rightsText = this.beheerderText;
          break;
      }
      radiobtn.checked = true;
    }, 100);
  }

  ngOnInit() {
    this.resetSaveButton();
    this.rightsText = this.studentText;
  }

}
