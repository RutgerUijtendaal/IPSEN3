import {Component, Input, OnInit} from '@angular/core';
import {AdminModel} from '../../../../../../shared/models/admin.model';

@Component({
  selector: 'app-admin-in-list',
  templateUrl: './admin-in-list.component.html',
  styleUrls: ['./admin-in-list.component.scss']
})
export class AdminInListComponent implements OnInit {

  @Input() admin: AdminModel;

  constructor() { }

  ngOnInit() {
  }

}
