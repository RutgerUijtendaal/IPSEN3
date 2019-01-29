import {Component, EventEmitter, OnInit, Output, ViewChild} from '@angular/core';
import {ModalDirective} from 'angular-bootstrap-md';

@Component({
  selector: 'app-delete-verify',
  templateUrl: './delete-verify.component.html',
  styleUrls: ['./delete-verify.component.scss']
})
export class DeleteVerifyComponent implements OnInit {

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
