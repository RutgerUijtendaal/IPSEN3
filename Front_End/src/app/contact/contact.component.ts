import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.scss']
})
export class ContactComponent implements OnInit {

  showLocation: boolean;
  showPhone: boolean;
  showEmail: boolean;

  constructor() {
    this.showLocation = true;
    this.showPhone = false;
    this.showEmail = false;
  }

  toggleLocation(): void {
    this.showLocation = true;
    this.showPhone = false;
    this.showEmail = false;
  }

  togglePhone(): void {
    this.showLocation = false;
    this.showPhone = true;
    this.showEmail = false;
  }

  toggleEmail(): void {
    this.showLocation = false;
    this.showPhone = false;
    this.showEmail = true;

  }

  ngOnInit() {
  }

}
