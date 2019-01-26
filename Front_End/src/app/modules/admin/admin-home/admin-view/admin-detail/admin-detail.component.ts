import { Component, OnInit } from '@angular/core';
import {AdminViewService} from '../admin-view.service';
import {HttpClient} from '@angular/common/http';
import {AppComponent} from '../../../../../app.component';
import {FormControl} from '@angular/forms';
import {ValidateEmail} from '../../../../../shared/validators/email.validator';
import {AdminModel} from '../../../../../shared/models/admin.model';

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

  getDetails() {
    this.edittedEmail = (<HTMLInputElement>document.getElementById('email-input')).value;
    this.edittedRightsId = (<HTMLInputElement>document.querySelector('input[name="rights"]:checked')).value;
  }

  saveNewAdmin() {
    this.viewService.admin.email = this.edittedEmail;
    this.viewService.admin.rightId = this.edittedRightsId;
    this.httpClient.post(this.URL + '/admin', this.viewService.admin).subscribe(retval => {
      if (Number(retval) === 0) {
        this.displayError('Toevoegen mislukt');
        return;
      } else {
        this.goodSave();
      }
      this.viewService.admin.id = Number(retval);
    }, error => {
      this.displayError('Toevoegen mislukt');
    });
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
    const updatedAdmin = new AdminModel(currentAdmin.id, currentAdmin.email, currentAdmin.password, currentAdmin.rightId);
    this.httpClient.put(this.URL + '/admin/' + updatedAdmin.id, updatedAdmin).subscribe(retval => {
      if (Number(retval) === 0) {
        this.displayError('Updaten mislukt');
      } else {
        this.goodSave();
      }
    }, error => {
      this.displayError('Updaten mislukt');
    });
  }

  goodSave() {
    this.saveButtonText = 'Opgeslagen';
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
