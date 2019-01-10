import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AppComponent } from '../../../../../app.component';
import { DilemmaListService } from './dilemma-list-service';
import { DilemmaModel } from '../../../../../shared/models/dilemma.model';

@Component({
  selector: 'app-dilemma-list',
  templateUrl: './dilemma-list.component.html',
  styleUrls: ['./dilemma-list.component.scss']
})
export class DilemmaListComponent implements OnInit {

  URL = AppComponent.environment.server;

  allDilemmas: DilemmaModel[];
  shownDilemmas: DilemmaModel[];
  oldSearch: string;
  currentSelectedDilemma: DilemmaModel;

  constructor(private service: DilemmaListService, private httpClient: HttpClient) {
    service.searchQuery.subscribe(search => this.updateList(search));
    this.allDilemmas = [];
    this.shownDilemmas = [];
    httpClient.get(this.URL + '/dilemma').subscribe(data =>
      this.createRecords(data as DilemmaModel[])
    );
  }

  createRecords(data: DilemmaModel[]) {
    data.forEach(dilemma => {
        /*
          const parent1: ParentModel = dilemma.parent1;
          const parent2: ParentModel = dilemma.parent2;
          parent1.id = dilemma.parent1.id;
          parent2.id = dilemma.parent2.id;
          */
        this.allDilemmas.push(new DilemmaModel());
      }
    );
    this.updateList('');
  }

  updateList(searchQuery: string) {
    this.oldSearch = searchQuery;
    this.shownDilemmas = this.allDilemmas.filter( dilemma =>
      // TODO
      console.log(dilemma)
  );
  }

  confirmDelete() {
    this.allDilemmas.splice(this.allDilemmas.findIndex(d => d.id === this.currentSelectedDilemma.id), 1);
    this.updateList(this.oldSearch);
    this.httpClient.delete(this.URL + '/dilemma/' + this.currentSelectedDilemma.id).subscribe((res) => {});
  }

  deleteRequest(dilemmaModel: DilemmaModel) {
    this.currentSelectedDilemma = dilemmaModel;
  }

  ngOnInit() {
  }

  /*
  createFakeRecords() {
    for (let i = 0; i < 20; i += 2) {
      this.allDilemmas.push(new DilemmaModel(
        new ParentModel('Foo' + String(i), String(i) + 'parentemail@gmail.com', '+31612345678'),
        new ParentModel('Bar' + String(i + 1), String(i + 1) + 'parentemail@gmail.com', '+31612345678')
      ));
    }
  }
  */

}
