import {Component, Input, OnInit} from '@angular/core';
import {ParentModel} from '../../../../shared/models/parent.model';

@Component({
  selector: 'app-parent-details',
  templateUrl: './parent-details.component.html',
  styleUrls: ['./parent-details.component.scss']
})
export class ParentDetailsComponent implements OnInit {
  @Input() parent: ParentModel;

  constructor() { }

  ngOnInit() {
  }

}
