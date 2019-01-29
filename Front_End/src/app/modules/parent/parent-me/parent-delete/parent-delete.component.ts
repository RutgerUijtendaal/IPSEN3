import {Component, OnInit, ViewChild} from '@angular/core';
import {map} from 'rxjs/operators';
import {AppComponent} from '../../../../app.component';
import {ParentDataService} from '../../parent-data.service';
import {DeleteVerifyComponent} from './delete-verify/delete-verify.component';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {AuthenticationService} from '../../../../core/auth/authentication.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-parent-delete',
  templateUrl: './parent-delete.component.html',
  styleUrls: ['./parent-delete.component.scss']
})
export class ParentDeleteComponent implements OnInit {

  private URL = AppComponent.environment.server;

  parentService: ParentDataService;

  @ViewChild(DeleteVerifyComponent)
  private deleteVerifyModal: DeleteVerifyComponent;

  constructor(parentService: ParentDataService,
              private authService: AuthenticationService,
              private router: Router,
              private http: HttpClient) {
    this.parentService = parentService;
  }
  ngOnInit() {
  }

  deleteAccountClicked() {
    this.deleteVerifyModal.show();

  }

  submitDelete() {
    let params = new HttpParams().set('token', this.parentService.couple.token);

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json'
      }),
      params: params,
    };

    this.http.delete(this.URL + '/couple/unregister/', httpOptions).pipe(
      map( (response) => {
        this.deleteVerifyModal.hide();
        this.authService.logout();
        this.router.navigate(['uitschrijven/' + this.parentService.couple.token + '/succes'])
      })
    ).subscribe()

  }

}
