import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UnregisterSuccessComponent } from './unregister-success.component';

describe('UnregisterSuccessComponent', () => {
  let component: UnregisterSuccessComponent;
  let fixture: ComponentFixture<UnregisterSuccessComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UnregisterSuccessComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UnregisterSuccessComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
