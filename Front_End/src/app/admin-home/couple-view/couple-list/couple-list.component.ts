import { Component, OnInit } from '@angular/core';
import {ParentModel} from '../../../models/parent.model';
import {CoupleModel} from '../../../models/couple.model';
import {CoupleListService} from '../couple-list-service';
import {HttpClient} from '@angular/common/http';
import {CoupleListModel} from '../../../models/couple-list.model';

@Component({
  selector: 'app-couple-list',
  templateUrl: './couple-list.component.html',
  styleUrls: ['./couple-list.component.scss']
})
export class CoupleListComponent implements OnInit {

  allCouples: CoupleModel[];
  shownCouples: CoupleModel[];

  constructor(service: CoupleListService, httpClient: HttpClient) {
    service.searchQuery.subscribe(search => this.updateList(search));
    this.allCouples = [];
    this.shownCouples = [];
    httpClient.get('http://localhost:8080/couple/getRegistry').subscribe(data =>
      this.createRealRecords(data as CoupleListModel[])
    );
  }

  createRealRecords(data: CoupleListModel[]) {
    data.forEach(couple =>
      this.allCouples.push(new CoupleModel(
        new ParentModel(couple.firstName1, couple.email1, couple.phoneNr1),
        new ParentModel(couple.firstName2, couple.email2, couple.phoneNr2)
      ))
    );
    this.updateList('');
  }

  updateList(searchQuery: string) {
    this.shownCouples = this.allCouples.filter( couple =>
      couple.parent1.email.includes(searchQuery) ||
      couple.parent2.email.includes(searchQuery) ||
      couple.parent1.phone.includes(searchQuery) ||
      couple.parent2.phone.includes(searchQuery)
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
