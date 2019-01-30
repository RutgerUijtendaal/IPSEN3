import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RelevancyChartComponent } from './relevancy-chart.component';

describe('RelevancyChartComponent', () => {
  let component: RelevancyChartComponent;
  let fixture: ComponentFixture<RelevancyChartComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RelevancyChartComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RelevancyChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
