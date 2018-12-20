import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable } from 'rxjs';
import {AuthenticationService} from './authentication.service';

@Injectable()
export class AuthenticationInterceptor implements HttpInterceptor {
  constructor(private authenticationService: AuthenticationService) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const accountModel = this.authenticationService.accountModel;
    if (accountModel.type) {
      request = request.clone({
        setHeaders: {
          Authorization: 'Basic ' + btoa(accountModel.email + ':' + accountModel.password)
        }
      });
    }

    return next.handle(request);
  }
}
