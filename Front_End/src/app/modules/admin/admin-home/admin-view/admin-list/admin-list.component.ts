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
    this.viewService.delete.subscribe(admin => this.deleteAdmin(admin));
  }

  deleteAdmin(admin: AdminModel) {
    this.allAdmins.splice(this.allAdmins.findIndex(a => a.id === admin.id), 1);
    this.updateList(this.oldSearch);
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

  unfinishedAdmin() {
    for (const admin of this.allAdmins) {
      if (admin.email.length === 0) {
        this.newAdminButtonText = 'Onafgemaakte beheerder';
        this.newAdminButtonClass = 'danger';
        setTimeout(() => {
          this.resetAddAdminButton();
        }, 1500);
        return admin;
      }
    }
  }

  newAdmin() {
    const newAdmin = new AdminModel(-1, '', '', '1');
    const val = this.unfinishedAdmin();
    if (val) {
      this.viewService.admin = val;
      this.viewService.adminClicked.next(0);
      return;
    }
    this.allAdmins.push(newAdmin);
    this.viewService.admin = newAdmin;
    this.viewService.adminClicked.next(0);
    this.updateList(this.oldSearch);
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
