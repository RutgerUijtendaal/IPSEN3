import {Component, Input, OnInit} from '@angular/core';
import {ParentModel} from '../../../models/parent.model';

@Component({
  selector: 'app-couple-in-list',
  templateUrl: './couple-in-list.component.html',
  styleUrls: ['./couple-in-list.component.scss']
})
export class CoupleInListComponent implements OnInit {

  @Input() parent1: ParentModel;
  @Input() parent2: ParentModel;

  constructor() { }

  ngOnInit() {
  }

}
