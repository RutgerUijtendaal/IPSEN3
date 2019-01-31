import {Component, Input, OnInit} from '@angular/core';
import {FormGroup} from '@angular/forms';

@Component({
  selector: 'app-login-card',
  templateUrl: './login-card.component.html',
  styleUrls: ['./login-card.component.scss']
})
export class LoginCardComponent implements OnInit {

  @Input() formTitle: string;
  @Input() adminLogin: FormGroup;

  constructor() {
  }

  ngOnInit() {
  }

}
