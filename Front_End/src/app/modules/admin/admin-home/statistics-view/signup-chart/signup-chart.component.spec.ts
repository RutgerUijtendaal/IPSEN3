import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SignupChartComponent } from './signup-chart.component';

describe('SignupChartComponent', () => {
  let component: SignupChartComponent;
  let fixture: ComponentFixture<SignupChartComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SignupChartComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SignupChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
