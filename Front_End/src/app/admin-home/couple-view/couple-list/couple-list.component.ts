import { Component, OnInit } from '@angular/core';
import {ParentModel} from '../../../models/parent.model';
import {CoupleModel} from '../../../models/couple.model';
import {CoupleListService} from '../couple-list-service';

@Component({
  selector: 'app-couple-list',
  templateUrl: './couple-list.component.html',
  styleUrls: ['./couple-list.component.scss']
})
export class CoupleListComponent implements OnInit {

  allCouples: CoupleModel[];
  shownCouples: CoupleModel[];

  constructor(service: CoupleListService) {
    service.searchQuery.subscribe(search => this.updateList(search));
    this.allCouples = [];
    this.createFakeRecords();
    this.updateList('');
  }

  createFakeRecords() {
    for (let i = 0; i < 100; i += 2) {
      this.allCouples.push(new CoupleModel(
        new ParentModel('naam' + String(i), 'email', 'phone'),
        new ParentModel('naam' + String(i + 1), 'email', 'phone')
      ));
    }
  }

  updateList(searchQuery: string) {
    this.shownCouples = this.allCouples.filter( couple =>
      couple.parent1.name.includes(searchQuery) ||
      couple.parent2.name.includes(searchQuery) ||
      couple.parent1.email.includes(searchQuery) ||
      couple.parent2.email.includes(searchQuery) ||
      couple.parent1.phone.includes(searchQuery) ||
      couple.parent2.phone.includes(searchQuery)
    );
  }

  ngOnInit() {
  }

}
