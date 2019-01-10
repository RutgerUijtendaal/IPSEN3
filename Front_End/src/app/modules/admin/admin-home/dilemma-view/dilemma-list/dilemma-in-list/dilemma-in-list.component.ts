import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-dilemma-in-list',
  templateUrl: './dilemma-in-list.component.html',
  styleUrls: ['./dilemma-in-list.component.scss']
})
export class DilemmaInListComponent implements OnInit {

  @Input() dilemma: DilemmaModel;
  @Output() delete: EventEmitter<DilemmaModel>;

  constructor() {
    this.delete = new EventEmitter();
  }

  ngOnInit() {
  }

  deleteRequest() {
    this.delete.emit(this.dilemma);
  }

}
