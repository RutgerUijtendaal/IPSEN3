import { Component, OnInit } from '@angular/core';
import {CoupleListService} from '../couple-list-service';

@Component({
  selector: 'app-couple-list-searchbar',
  templateUrl: './couple-list-searchbar.component.html',
  styleUrls: ['./couple-list-searchbar.component.scss']
})
export class CoupleListSearchbarComponent implements OnInit {

  service: CoupleListService;

  constructor(service: CoupleListService) {
    this.service = service;
  }

  newInput(event: any) {
    this.service.searchQuery.next(event.value);
  }

  ngOnInit() {
  }

}
