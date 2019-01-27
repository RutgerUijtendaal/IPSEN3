import {Component, EventEmitter, OnInit, Output, ViewChild} from '@angular/core';
import {ModalDirective} from 'angular-bootstrap-md';

@Component({
  selector: 'app-born-verify',
  templateUrl: './born-verify.component.html',
  styleUrls: ['./born-verify.component.scss']
})
export class BornVerifyComponent implements OnInit {

  @ViewChild('modal')
  private modal: ModalDirective;

  @Output()
  private verify: EventEmitter<void> = new EventEmitter<void>();

  constructor() { }

  ngOnInit() {
  }

  hide() {
    this.modal.hide();
  }

  show() {
    this.modal.show();
  }

  verifyAnswer() {
    this.modal.hide();
    this.verify.emit();
  }


}
