import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {CoupleModel} from '../../../../models/couple.model';

@Component({
  selector: 'app-couple-in-list',
  templateUrl: './couple-in-list.component.html',
  styleUrls: ['./couple-in-list.component.scss']
})
export class CoupleInListComponent implements OnInit {

  @Input() couple: CoupleModel;
  @Output() delete: EventEmitter<CoupleModel>;

  constructor() {
    this.delete = new EventEmitter();
  }

  ngOnInit() {
  }

  deleteRequest() {
    this.delete.emit(this.couple);
  }

}
