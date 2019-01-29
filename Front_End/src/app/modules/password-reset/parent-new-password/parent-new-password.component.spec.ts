import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParentNewPasswordComponent } from './parent-new-password.component';

describe('ParentNewPasswordComponent', () => {
  let component: ParentNewPasswordComponent;
  let fixture: ComponentFixture<ParentNewPasswordComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ParentNewPasswordComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ParentNewPasswordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
