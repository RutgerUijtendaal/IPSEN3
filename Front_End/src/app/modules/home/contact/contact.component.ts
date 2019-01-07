import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.scss']
})
export class ContactComponent implements OnInit {

  UNSELECTED = '#1e4768';
  SELECTED = '#03142b';
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
    this.locationColor = this.UNSELECTED;
    this.phoneColor = this.UNSELECTED;
    this.mailColor = this.UNSELECTED;
  }


  toggleLocation(): void {
    this.defaults();
    this.displayTitle = 'Locatie';
    this.displayDetails = this.locationDetails;
    this.locationColor = this.SELECTED;
  }

  togglePhone(): void {
    this.defaults();
    this.displayTitle = 'Telefoon';
    this.displayDetails = this.phoneDetails;
    this.phoneColor = this.SELECTED;
  }

  toggleEmail(): void {
    this.defaults();
    this.displayTitle = 'Email';
    this.displayDetails = this.mailDetails;
    this.mailColor = this.SELECTED;
  }

  ngOnInit() {
  }

}
