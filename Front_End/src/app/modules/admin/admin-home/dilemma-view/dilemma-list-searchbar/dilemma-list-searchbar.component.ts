import { Component, OnInit } from '@angular/core';
import { DilemmaListService } from '../dilemma-list/dilemma-list-service';

@Component({
  selector: 'app-dilemma-list-searchbar',
  templateUrl: './dilemma-list-searchbar.component.html',
  styleUrls: ['./dilemma-list-searchbar.component.scss']
})
export class DilemmaListSearchbarComponent implements OnInit {

  constructor(private service: DilemmaListService) { }

  newInput(event: any) {
    this.service.searchQuery.next(event.value);
  }

  ngOnInit() {
  }

}
