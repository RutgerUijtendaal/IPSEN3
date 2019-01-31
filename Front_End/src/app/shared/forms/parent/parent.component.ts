import {Component, Input, OnInit} from '@angular/core';
import {FormGroup} from '@angular/forms';

@Component({
  selector: 'app-parent',
  templateUrl: './parent.component.html',
  styleUrls: ['./parent.component.scss']
})
export class ParentComponent implements OnInit {
  @Input() parent: FormGroup;
  @Input() formTitle: string;
  @Input() id: number;

  constructor() { }

  ngOnInit() {
  }

}
