import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.scss']
})
export class ContactComponent implements OnInit {

  LIGHT_COLOR = '#1e4768';
  DARK_COLOR = '#03142b';

  locationColor: string;
  phoneColor: string;
  mailColor: string;

  showLocation: boolean;
  showPhone: boolean;
  showEmail: boolean;


  constructor() {
    this.mailColor = this.DARK_COLOR;
    this.showLocation = false;
    this.showPhone = false;
    this.showEmail = true;
  }

  toggleLocation(): void {
    this.showLocation = true;
    this.locationColor = this.DARK_COLOR;
    this.showPhone = false;
    this.phoneColor = this.LIGHT_COLOR;
    this.showEmail = false;
    this.mailColor = this.LIGHT_COLOR;
  }

  togglePhone(): void {
    this.showLocation = false;
    this.locationColor = this.LIGHT_COLOR;
    this.showPhone = true;
    this.phoneColor = this.DARK_COLOR;
    this.showEmail = false;
    this.mailColor = this.LIGHT_COLOR;
  }

  toggleEmail(): void {
    this.showLocation = false;
    this.locationColor = this.LIGHT_COLOR;
    this.showPhone = false;
    this.phoneColor = this.LIGHT_COLOR;
    this.showEmail = true;
    this.mailColor = this.DARK_COLOR;
  }

  ngOnInit() {
  }

}
