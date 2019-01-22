import { Component, OnInit } from '@angular/core';
import {AdminListService} from '../admin-list.service';

@Component({
  selector: 'app-admin-list-searchbar',
  templateUrl: './admin-list-searchbar.component.html',
  styleUrls: ['./admin-list-searchbar.component.scss']
})
export class AdminListSearchbarComponent implements OnInit {

  constructor(private service: AdminListService) {
  }

  newInput(event: any) {
    this.service.searchQuery.next(event.value);
  }

  ngOnInit() {
  }

}
