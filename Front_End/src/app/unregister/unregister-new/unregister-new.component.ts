import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {UnregisterService} from './unregister.service';

@Component({
  selector: 'app-unregister-new',
  templateUrl: './unregister-new.component.html',
  styleUrls: ['./unregister-new.component.scss']
})
export class UnregisterNewComponent implements OnInit, OnDestroy {
  unregisterLoading = false;
  showError = false;
  errorMessage = '';
  routeSub: any;
  token: string;


  constructor(private router: Router, private service: UnregisterService, private route: ActivatedRoute) {

  }

  ngOnInit() {
    this.routeSub = this.route.params.subscribe(params => {
      this.token = params['token'];
    });
    console.log(this.token);
  }

  unregister() {
    this.unregisterLoading = true;

    this.service.unregister(this.token).subscribe(
      (message: any) => {
        this.unregisterLoading = false;
        this.router.navigate(['succes'], {relativeTo: this.route});
      },
      (error: any) => {
        this.unregisterLoading = false;
        this.errorMessage = 'Er is iets fout gegaan tijdens het uitschrijven';
        this.showError = true;
      });

  }

  ngOnDestroy() {
    this.routeSub.unsubscribe();
  }
}