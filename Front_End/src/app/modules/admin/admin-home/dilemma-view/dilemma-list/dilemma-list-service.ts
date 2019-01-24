import {Injectable} from '@angular/core';
import {Subject} from 'rxjs';

@Injectable()
export class DilemmaListService {
  searchQuery: Subject<string>;

  constructor() {
    this.searchQuery = new Subject();
    this.searchQuery.asObservable();
  }

}
