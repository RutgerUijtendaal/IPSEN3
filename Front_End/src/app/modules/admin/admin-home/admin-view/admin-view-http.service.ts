import {Injectable} from '@angular/core';
import {Subject} from 'rxjs';
import {AppComponent} from '../../../../app.component';
import {HttpClient} from '@angular/common/http';
import {AdminModel} from '../../../../shared/models/admin.model';

@Injectable()
export class AdminViewHttpService {

  private URL = AppComponent.environment.server;

  success: Subject<string>;
  failure: Subject<string>;
  loadedAdmins: Subject<any>;

  constructor(private httpClient: HttpClient) {
    this.success = new Subject();
    this.failure = new Subject();
    this.loadedAdmins = new Subject();
    this.success.asObservable();
    this.failure.asObservable();
    this.loadedAdmins.asObservable();
  }


  saveNewAdmin(admin: AdminModel) {
    this.httpClient.post(this.URL + '/admin', admin).subscribe(retval => {
      if (Number(retval) === 0) {
        this.failure.next('Toevoegen mislukt');
      } else {
        this.success.next('Opgeslagen');
      }
      admin.id = Number(retval);
    }, error => {
      this.failure.next('Geen verbinding');
    });
  }

  updateAdmin(admin: AdminModel) {
    this.httpClient.put(this.URL + '/admin/' + admin.id, admin).subscribe(retval => {
      if (Number(retval) === 0) {
        this.failure.next('Updaten mislukt');
      } else {
        this.success.next('Opgeslagen');
      }
    }, error => {
      this.failure.next('Geen verbinding');
    });
  }

  loadAdmins() {
    const admins: AdminModel[] = [];
    this.httpClient.get(this.URL + '/admin').subscribe(data => {
      (data as AdminModel[]).forEach(admin => admins.push(admin));
      this.loadedAdmins.next(0);
    });
    return admins;
  }

  deleteAdmin(id: number) {
    this.httpClient.delete(this.URL + /admin/ + id).subscribe(res => {
    });
  }

}
