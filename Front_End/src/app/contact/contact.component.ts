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
    this.showPhone = true;
    this.showEmail = true;
  }

  toggleLocation(): void {
    this.showLocation = !this.showLocation;
  }

  togglePhone(): void {
    this.showPhone = !this.showPhone;
  }

  toggleEmail(): void {
    this.showEmail = !this.showEmail;

  }

  ngOnInit() {
  }

}
