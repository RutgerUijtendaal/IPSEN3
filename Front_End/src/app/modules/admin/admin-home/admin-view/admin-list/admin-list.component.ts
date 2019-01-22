import { Component, OnInit } from '@angular/core';
import { AppComponent } from '../../../../../app.component';
import {AdminModel} from '../../../../../shared/models/admin.model';
import {HttpClient} from '@angular/common/http';
import {AdminListService} from '../admin-list.service';

@Component({
  selector: 'app-admin-list',
  templateUrl: './admin-list.component.html',
  styleUrls: ['./admin-list.component.scss']
})
export class AdminListComponent implements OnInit {

  URL = AppComponent.environment.server;

  shownAdmins: AdminModel[];
  allAdmins: AdminModel[];

  newAdminButtonClass: string;
  newAdminButtonText: string;

  oldSearch: string;

  constructor(private httpClient: HttpClient, private service: AdminListService) {
    this.loadAdmins();
    this.service.searchQuery.subscribe(searchQuery => this.updateList(searchQuery));
  }

  resetAddAdminButton() {
    this.newAdminButtonClass = 'primary';
    this.newAdminButtonText = 'Nieuwe beheerder';
  }

  loadAdmins() {
    this.allAdmins = [];
    this.shownAdmins = [];
    this.httpClient.get(this.URL + '/admin').subscribe(data => {
      this.createAdminRecords(data as AdminModel[]);
      this.updateList('');
    });
  }

  createAdminRecords(data: AdminModel[]) {
    this.allAdmins = data;
    this.allAdmins.sort((a1, a2) => a1.rightId > a2.rightId ? 1 : -1);
    console.log(this.allAdmins.length);
  }

  adminClicked(admin: AdminModel) {

  }

  newAdmin() {

  }

  updateList(searchQuery: string) {
    searchQuery = searchQuery.toLocaleLowerCase();
    this.oldSearch = searchQuery;
    this.shownAdmins = this.allAdmins.filter( admin =>
      String(admin.email).includes(searchQuery)
    );
    this.allAdmins.sort((a1, a2) => a1.rightId > a2.rightId ? 1 : -1);
    this.shownAdmins.sort((a1, a2) => a1.rightId > a2.rightId ? 1 : -1);
  }

  ngOnInit() {
    this.resetAddAdminButton();
    this.updateList('');
  }

}
