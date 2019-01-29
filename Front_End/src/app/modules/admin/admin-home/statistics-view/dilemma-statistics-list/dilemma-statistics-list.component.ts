import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AppComponent } from '../../../../../app.component';
import { DilemmaModel } from '../../../../../shared/models/dilemma.model';
import { DilemmaListService } from '../../dilemma-view/dilemma-list/dilemma-list-service';
import { StatisticsService } from '../statistics.service';

@Component({
  selector: 'app-statistics-dilemma-list',
  templateUrl: './dilemma-statistics-list.component.html',
  styleUrls: ['./dilemma-statistics-list.component.scss']
})
export class DilemmaStatisticsListComponent implements OnInit {

  URL = AppComponent.environment.server;

  allDilemmas: DilemmaModel[];
  shownDilemmas: DilemmaModel[];
  oldSearch: string;
  periods: Period[] = [];
  period: string;

  constructor(private listService: DilemmaListService,
              private httpClient: HttpClient,
              private statisticsService: StatisticsService) {
    listService.searchQuery.subscribe(search => this.updateList(search));
    this.shownDilemmas = [];
    this.periods.push(new Period('voor', 'Voor Geboorte'));
    this.periods.push(new Period('na', 'Na Geboorte'));
    this.period = this.periods[0].link;
    this.loadDilemmas('voor');
  }

  loadDilemmas(period: string) {
    this.allDilemmas = [];
    this.httpClient.get(this.URL + '/dilemma/' + period).subscribe(data =>
      this.createDilemmaRecords(data as DilemmaModel[])
    );
    this.allDilemmas.sort((d1, d2) => d1.weekNr > d2.weekNr ? 1 : -1);
  }

  createDilemmaRecords(data: DilemmaModel[]) {
    this.allDilemmas = data;
    this.allDilemmas.sort((d1, d2) => d1.weekNr > d2.weekNr ? 1 : -1);
    this.updateList('');
  }

  dilemmaClicked(dilemma: DilemmaModel) {
    const indexOf = this.statisticsService.dilemmas.indexOf(dilemma.id);
    if (indexOf > -1) {
      this.statisticsService.dilemmas.splice(indexOf, 1);
    } else {
      this.statisticsService.dilemmas.push(dilemma.id);
    }
    this.statisticsService.update();
  }

  updateList(searchQuery: string) {
    searchQuery = searchQuery.toLocaleLowerCase();
    this.oldSearch = searchQuery;
    this.shownDilemmas = this.allDilemmas.filter(dilemma =>
      String(dilemma.weekNr).includes(searchQuery) ||
      dilemma.theme.toLocaleLowerCase().includes(searchQuery)
    );
    this.shownDilemmas.sort((a, b) => (a.weekNr > b.weekNr) ? 1 : 0);
  }

  onChange(value: string) {
    this.period = value;
    this.loadDilemmas(value);
  }

  ngOnInit() {
  }
}

class Period {
  constructor(public link: string, public name: string) {
  }

}
