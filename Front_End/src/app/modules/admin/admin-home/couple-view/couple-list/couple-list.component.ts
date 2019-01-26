import { Component, OnInit } from '@angular/core';
import {ParentModel} from '../../../../../shared/models/parent.model';
import {CoupleModel} from '../../../../../shared/models/couple.model';
import {CoupleListService} from '../couple-list-service';
import {HttpClient} from '@angular/common/http';
import {AppComponent} from '../../../../../app.component';

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
    searchQuery = searchQuery.toLocaleLowerCase()
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
    this.httpClient.delete(this.URL + '/couple/' + this.currentSelectedCouple.coupleId).subscribe((res) => {
      console.log(res.toString());
    });
  }

  deleteRequest(coupleModel: CoupleModel) {
    this.currentSelectedCouple = coupleModel;
  }

  ngOnInit() {
  }

  /*
  createFakeRecords() {
    for (let i = 0; i < 20; i += 2) {
      this.allCouples.push(new CoupleModel(
        new ParentModel('Foo' + String(i), String(i) + 'parentemail@gmail.com', '+31612345678'),
        new ParentModel('Bar' + String(i + 1), String(i + 1) + 'parentemail@gmail.com', '+31612345678')
      ));
    }
  }
  */
}
