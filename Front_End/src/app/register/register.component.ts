import { Component, ContentChild, OnInit, ViewChild } from '@angular/core';
import { ParentModel } from '../form/model/parent/parent.model';
import { ParentComponent } from '../form/model/parent/parent.component';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  parentModelOne: ParentModel = new ParentModel(null, null, null);
  parentModelTwo: ParentModel = new ParentModel(null, null, null);

  constructor() {
  }

  ngOnInit() {
  }

  getFormData(): void {
    console.log(this.parentModelOne.name);
  }

}
