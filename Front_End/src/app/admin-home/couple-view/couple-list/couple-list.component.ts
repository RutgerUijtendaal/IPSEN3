import { Component, OnInit } from '@angular/core';
import {ParentModel} from '../../../models/parent.model';
import {CoupleModel} from '../../../models/couple.model';

@Component({
  selector: 'app-couple-list',
  templateUrl: './couple-list.component.html',
  styleUrls: ['./couple-list.component.scss']
})
export class CoupleListComponent implements OnInit {

  couples: CoupleModel[];

  constructor() {
    this.couples = [];
    const tmpParent1: ParentModel = new ParentModel('naam1', 'email1', 'phone1');
    const tmpParent2: ParentModel = new ParentModel('naam2', 'email2', 'phone2');
    const tmpCouple: CoupleModel = new CoupleModel(tmpParent1, tmpParent2);
    this.couples.push(tmpCouple);
  }

  ngOnInit() {
  }

}
