import {Component, Input, OnInit, Output} from '@angular/core';
import {ParentModel} from '../../../../models/parent.model';
import {CoupleModel} from '../../../../models/couple.model';

@Component({
  selector: 'app-couple-in-list',
  templateUrl: './couple-in-list.component.html',
  styleUrls: ['./couple-in-list.component.scss']
})
export class CoupleInListComponent implements OnInit {

  @Input() couple: CoupleModel;

  constructor() { }

  ngOnInit() {
  }

  deleteRequest() {
    console.log('coupleId: ' + this.couple.coupleId);
    console.log('parent1: ' + this.couple.parent1.id);
    console.log('parent2: ' + this.couple.parent2.id);
  }

}
