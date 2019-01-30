import { Component, OnInit } from '@angular/core';
import {CoupleListService} from '../../../couple-view/couple-list-service';

@Component({
  selector: 'app-couple-statistics-filter',
  templateUrl: './couple-statistics-filter.component.html',
  styleUrls: ['./couple-statistics-filter.component.scss']
})
export class CoupleStatisticsFilterComponent implements OnInit {

  constructor(private service: CoupleListService) {
  }

  newInput(event: any) {
    this.service.searchQuery.next(event.value);
  }

  ngOnInit() {
  }

}
