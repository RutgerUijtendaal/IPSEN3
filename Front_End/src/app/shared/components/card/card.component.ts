import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-widget-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.scss']
})
export class CardComponent implements OnInit {

  @Input()
  image: string;

  @Input()
  title: string;

  @Input()
  alt: string;

  constructor() { }

  ngOnInit() {
  }

}
