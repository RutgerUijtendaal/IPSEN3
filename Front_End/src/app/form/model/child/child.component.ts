import {Component, Input, OnInit} from '@angular/core';
import {FormGroup} from '@angular/forms';

@Component({
  selector: 'app-child',
  templateUrl: './child.component.html',
  styleUrls: ['./child.component.scss']
})
export class ChildComponent implements OnInit {
  @Input() child: FormGroup;
  @Input() formTitle: string;
  dateText: string;



  constructor() { }

  ngOnInit() {
  }

}
