import { Component, OnInit } from '@angular/core';
import {ParentModel} from '../../../models/parent.model';
import {CoupleModel} from '../../../models/couple.model';
import {CoupleListService} from '../couple-list-service';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-couple-list',
  templateUrl: './couple-list.component.html',
  styleUrls: ['./couple-list.component.scss']
})
export class CoupleListComponent implements OnInit {

  URL = 'http://localhost:8080/couple/all-list';
  allCouples: CoupleModel[];
  shownCouples: CoupleModel[];

  constructor(service: CoupleListService, httpClient: HttpClient) {
    service.searchQuery.subscribe(search => this.updateList(search));
    this.allCouples = [];
    this.shownCouples = [];
    httpClient.get(this.URL).subscribe(data =>
      this.createRecords(data as CoupleModel[])
    );
  }

  createRecords(data: CoupleModel[]) {
    data.forEach(couple => {
        const parent1: ParentModel = new ParentModel(couple.parent1.firstName, couple.parent1.email, couple.parent1.phoneNr);
        const parent2: ParentModel = new ParentModel(couple.parent2.firstName, couple.parent2.email, couple.parent2.phoneNr);
        parent1.id = couple.parent1.id;
        parent2.id = couple.parent2.id;
        this.allCouples.push(new CoupleModel(couple.coupleId, parent1, parent2));
      }
    );
    this.updateList('');
  }

  updateList(searchQuery: string) {
    this.shownCouples = this.allCouples.filter( couple =>
      couple.parent1.email.includes(searchQuery) ||
      couple.parent2.email.includes(searchQuery) ||
      couple.parent1.phoneNr.includes(searchQuery) ||
      couple.parent2.phoneNr.includes(searchQuery)
    );
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
