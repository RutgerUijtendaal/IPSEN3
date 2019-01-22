import {Injectable} from '@angular/core';
import {AdminModel} from '../../../../shared/models/admin.model';

@Injectable()
export class AdminViewService {

  admin: AdminModel;

  constructor() {
    this.admin = null;
  }

}
