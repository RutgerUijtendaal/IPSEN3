import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminNewPasswordComponent } from './admin-new-password.component';

describe('AdminNewPasswordComponent', () => {
  let component: AdminNewPasswordComponent;
  let fixture: ComponentFixture<AdminNewPasswordComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminNewPasswordComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminNewPasswordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
