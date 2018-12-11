import { Component, EventEmitter, Input, OnChanges, OnInit, Output } from '@angular/core';
import { ParentModel } from './parent.model';

@Component({
  selector: 'app-parent',
  templateUrl: './parent.component.html',
  styleUrls: ['./parent.component.scss']
})
export class ParentComponent implements OnInit, OnChanges {

  @Input()
  title: string;

  @Input()
  parent: ParentModel;

  constructor() { }

  ngOnInit() {
  }

  ngOnChanges() {
    console.log(this.parent.name);
  }

}
