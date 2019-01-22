import { Component, OnInit } from '@angular/core';
import { AppComponent } from '../../../../../app.component';
import {AdminModel} from '../../../../../shared/models/admin.model';
import {HttpClient} from '@angular/common/http';
import {AdminListService} from './admin-list.service';
import {AdminViewService} from '../admin-view.service';

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

  constructor(private httpClient: HttpClient, private listService: AdminListService, private viewService: AdminViewService) {
    this.loadAdmins();
    this.listService.searchQuery.subscribe(searchQuery => this.updateList(searchQuery));
  }

  resetAddAdminButton() {
    this.newAdminButtonClass = 'primary';
    this.newAdminButtonText = 'Nieuwe beheerder';
  }

  loadAdmins() {
    this.allAdmins = [];
    this.shownAdmins = [];
    this.httpClient.get(this.URL + '/admin').subscribe(data => {
      this.allAdmins = data as AdminModel[];
      this.updateList('');
    });
  }

  adminClicked(admin: AdminModel) {
    this.viewService.admin = admin;
    this.viewService.adminClicked.next(0);
  }

  newAdmin() {
  }

  updateList(searchQuery: string) {
    searchQuery = searchQuery.toLocaleLowerCase();
    this.oldSearch = searchQuery;
    this.shownAdmins = this.allAdmins.filter( admin =>
      String(admin.email).includes(searchQuery)
    );
    this.shownAdmins.sort((a1, a2) => a1.rightId > a2.rightId ? 1 : -1);
  }

  ngOnInit() {
    this.resetAddAdminButton();
    this.updateList('');
  }

}
