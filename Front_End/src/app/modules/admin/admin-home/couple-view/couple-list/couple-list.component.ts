import { Component, OnInit } from '@angular/core';
import {ParentModel} from '../../../../../shared/models/parent.model';
import {CoupleModel} from '../../../../../shared/models/couple.model';
import {CoupleListService} from '../couple-list-service';
import {HttpClient} from '@angular/common/http';
import {AppComponent} from '../../../../../app.component';
import {CoupleViewHttpService} from '../couple-view-http.service';

@Component({
  selector: 'app-couple-list',
  templateUrl: './couple-list.component.html',
  styleUrls: ['./couple-list.component.scss']
})
export class CoupleListComponent implements OnInit {

  URL = AppComponent.environment.server;

  allCouples: CoupleModel[];
  shownCouples: CoupleModel[];
  oldSearch: string;
  currentSelectedCouple: CoupleModel;

  constructor(private service: CoupleListService,
              private httpService: CoupleViewHttpService) {
    this.service.searchQuery.subscribe(search => this.updateList(search));
    this.httpService.success.subscribe(val => this.updateList(''));
    this.shownCouples = [];
    this.allCouples = this.httpService.loadCouples();
  }

  updateList(searchQuery: string) {
    searchQuery = searchQuery.toLocaleLowerCase();
    this.oldSearch = searchQuery;
    this.shownCouples = this.allCouples.filter( couple =>
      couple.parent1.email.toLocaleLowerCase().includes(searchQuery) ||
      couple.parent2.email.toLocaleLowerCase().includes(searchQuery) ||
      couple.parent1.phoneNr.includes(searchQuery) ||
      couple.parent2.phoneNr.includes(searchQuery)
    );
  }

  confirmDelete() {
    this.allCouples.splice(this.allCouples.findIndex(c => c.coupleId === this.currentSelectedCouple.coupleId), 1);
    this.updateList(this.oldSearch);
    this.httpService.deleteCouple(this.currentSelectedCouple);
  }

  deleteRequest(coupleModel: CoupleModel) {
    this.currentSelectedCouple = coupleModel;
  }

  ngOnInit() {
  }

}
