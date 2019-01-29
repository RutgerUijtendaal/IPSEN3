import {Injectable} from '@angular/core';
import {AdminModel} from '../../../../shared/models/admin.model';
import {Subject} from 'rxjs';

@Injectable()
export class AdminViewService {

  admin: AdminModel;
  adminClicked: Subject<any>;
  delete: Subject<any>;

  constructor() {
    this.admin = null;
    this.adminClicked = new Subject();
    this.delete = new Subject();
    this.adminClicked.asObservable();
    this.delete.asObservable();
  }

}
