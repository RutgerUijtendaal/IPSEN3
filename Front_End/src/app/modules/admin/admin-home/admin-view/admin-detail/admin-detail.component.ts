import { Component, OnInit } from '@angular/core';
import {AdminViewService} from '../admin-view.service';
import {AppComponent} from '../../../../../app.component';
import {FormControl} from '@angular/forms';
import {ValidateEmail} from '../../../../../shared/validators/email.validator';
import {AdminModel} from '../../../../../shared/models/admin.model';
import {AdminViewHttpService} from '../admin-view-http.service';

@Component({
  selector: 'app-admin-detail',
  templateUrl: './admin-detail.component.html',
  styleUrls: ['./admin-detail.component.scss']
})
export class AdminDetailComponent implements OnInit {

  URL = AppComponent.environment.server;

  viewService: AdminViewService;

  edittedEmail: string;
  edittedRightsId: string;

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

  constructor(viewService: AdminViewService, private httpService: AdminViewHttpService) {
    this.viewService = viewService;
    this.viewService.adminClicked.subscribe(val => this.updateRadioButtons());
    this.httpService.success.subscribe(message => this.goodSave(message));
    this.httpService.failure.subscribe(message => this.displayError(message));
  }

  deleteAdmin() {
    this.httpService.deleteAdmin(this.viewService.admin.id);
    this.viewService.delete.next(this.viewService.admin);
    this.viewService.admin = null;
  }

  getDetails() {
    this.edittedEmail = (<HTMLInputElement>document.getElementById('email-input')).value;
    this.edittedRightsId = (<HTMLInputElement>document.querySelector('input[name="rights"]:checked')).value;
  }

  saveNewAdmin() {
    this.viewService.admin.email = this.edittedEmail;
    this.viewService.admin.rightId = this.edittedRightsId;
    this.httpService.saveNewAdmin(this.viewService.admin);
  }

  displayError(message: string) {
    this.saveButtonText = message;
    this.saveButtonClass = 'danger';
    setTimeout(() => {
      this.resetSaveButton();
    }, 2000);
  }

  updateAdmin() {
    this.viewService.admin.email = this.edittedEmail;
    this.viewService.admin.rightId = this.edittedRightsId;
    const currentAdmin = this.viewService.admin;
    const updatedAdmin = new AdminModel(currentAdmin.id, currentAdmin.email, currentAdmin.password, currentAdmin.rightId, null);
    this.httpService.updateAdmin(updatedAdmin);
  }

  goodSave(message: string) {
    this.saveButtonText = message;
    this.saveButtonClass = 'success';
    setTimeout(() => {
      this.resetSaveButton();
    }, 1500);
  }


  saveRequest() {
    this.getDetails();
    const form = new FormControl(this.edittedEmail);
    if (ValidateEmail(form)) {
      this.displayError('Foute email');
      return;
    }
    if (this.viewService.admin.id === -1) {
      this.saveNewAdmin();
    } else {
      this.updateAdmin();
    }
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
