import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.scss']
})
export class ContactComponent implements OnInit {

  LIGHT_COLOR = '#1e4768';
  DARK_COLOR = '#03142b';
  phoneDetails = 'TELEFOON Ipsum is slechts een proeftekst uit het drukkerij- en zetterijwezen. Lorem Ipsum is de standaard proeftekst in deze bedrijfstak sinds de 16e eeuw, toen een onbekende drukker een zethaak met letters nam en ze door elkaar husselde om een font-catalogus te maken.';
  mailDetails = 'EMAIL Ipsum is slechts een proeftekst uit het drukkerij- en zetterijwezen. Lorem Ipsum is de standaard proeftekst in deze bedrijfstak sinds de 16e eeuw, toen een onbekende drukker een zethaak met letters nam en ze door elkaar husselde om een font-catalogus te maken.';
  locationDetails = 'LOCATIE Ipsum is slechts een proeftekst uit het drukkerij- en zetterijwezen. Lorem Ipsum is de standaard proeftekst in deze bedrijfstak sinds de 16e eeuw, toen een onbekende drukker een zethaak met letters nam en ze door elkaar husselde om een font-catalogus te maken.';

  displayTitle: string;
  displayDetails: string;

  locationColor: string;
  phoneColor: string;
  mailColor: string;

  constructor() {
    this.defaults();
    this.toggleEmail();
  }

  defaults() {
    this.locationColor = this.LIGHT_COLOR;
    this.phoneColor = this.LIGHT_COLOR;
    this.mailColor = this.LIGHT_COLOR;
  }


  toggleLocation(): void {
    this.defaults();
    this.displayTitle = 'Locatie';
    this.displayDetails = this.locationDetails;
    this.locationColor = this.DARK_COLOR;
  }

  togglePhone(): void {
    this.defaults();
    this.displayTitle = 'Telefoon';
    this.displayDetails = this.phoneDetails;
    this.phoneColor = this.DARK_COLOR;
  }

  toggleEmail(): void {
    this.defaults();
    this.displayTitle = 'Email';
    this.displayDetails = this.mailDetails;
    this.mailColor = this.DARK_COLOR;
  }

  ngOnInit() {
  }

}
