import { Component, OnInit } from '@angular/core';
import {ParentModel} from '../../../../../shared/models/parent.model';
import {CoupleModel} from '../../../../../shared/models/couple.model';
import {HttpClient} from '@angular/common/http';
import {AppComponent} from '../../../../../app.component';
import { CoupleListService } from '../../couple-view/couple-list-service';

@Component({
  selector: 'app-statistics-couple-list',
  templateUrl: './couple-statistics-list.component.html',
  styleUrls: ['./couple-statistics-list.component.scss']
})
export class CoupleStatisticsListComponent implements OnInit {

  URL = AppComponent.environment.server;

  allCouples: CoupleModel[];
  shownCouples: CoupleModel[];
  oldSearch: string;

  constructor(private service: CoupleListService, private httpClient: HttpClient) {
    service.searchQuery.subscribe(search => this.updateList(search));
    this.allCouples = [];
    this.shownCouples = [];
    httpClient.get(this.URL + '/couple-list').subscribe(data =>
      this.createRecords(data as CoupleModel[])
    );
  }

  createRecords(data: CoupleModel[]) {
    data.forEach(couple => {
        const parent1: ParentModel = couple.parent1;
        const parent2: ParentModel = couple.parent2;
        parent1.id = couple.parent1.id;
        parent2.id = couple.parent2.id;
        this.allCouples.push(new CoupleModel(couple.coupleId, parent1, parent2, couple['date'], couple['token']));
      }
    );
    this.updateList('');
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

  ngOnInit() {
  }

}
