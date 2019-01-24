import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { ModalDirective } from 'angular-bootstrap-md';

@Component({
  selector: 'app-dilemma-loading',
  templateUrl: './dilemma-loading.component.html',
  styleUrls: ['./dilemma-loading.component.scss']
})
export class DilemmaLoadingComponent implements OnInit {

  @ViewChild('modal')
  private modal: ModalDirective;

  constructor() { }

  ngOnInit() {
  }

  public hide(): void {
    this.modal.hide();
  }

  public show(): void {
    this.modal.show();
  }

}
