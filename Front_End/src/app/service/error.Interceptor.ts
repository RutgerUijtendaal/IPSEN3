import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import {AuthenticationService} from './authentication.service';
import {Router} from '@angular/router';

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {


  constructor(private autenticationService: AuthenticationService, private router: Router) {

  }


  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(request).pipe(catchError(err => {
      if (err.status === 401) {
        // TODO PROPER LOG OUT
        this.autenticationService.accountModel.type = null;
        this.router.navigate(['/forbidden']);
        // location.reload(true);
      }
      const error = err.error.message || err.statusText;
      return throwError(error);
    }));
  }
}
