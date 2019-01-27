import {Component, OnInit, ViewChild} from '@angular/core';
import {ParentDataService} from '../../parent-data.service';
import {BornVerifyComponent} from './born-verify/born-verify.component';
import {HttpClient} from '@angular/common/http';
import {ChildModel} from '../../../../shared/models/child.model';
import {AppComponent} from '../../../../app.component';
import {map} from 'rxjs/operators';

@Component({
  selector: 'app-parent-child-born',
  templateUrl: './parent-child-born.component.html',
  styleUrls: ['./parent-child-born.component.scss']
})
export class ParentChildBornComponent implements OnInit {

  private URL = AppComponent.environment.server;

  parentService: ParentDataService;

  @ViewChild(BornVerifyComponent)
  private bornVerifyModal: BornVerifyComponent;

  constructor(parentService: ParentDataService,
              private http: HttpClient) {
    this.parentService = parentService;
  }

  ngOnInit() {
  }

  childBornClick() {
    this.bornVerifyModal.show();

  }

  submitBorn() {
    this.http.post<ChildModel>(this.URL + '/child/born/' + this.parentService.child.id, {}).pipe(
      map( (response) => {
        this.bornVerifyModal.hide();
        this.parentService.child.isBorn = true;
      })
    ).subscribe()

  }
}
