import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AnswerChartComponent } from './answer-chart.component';

describe('AnswerChartComponent', () => {
  let component: AnswerChartComponent;
  let fixture: ComponentFixture<AnswerChartComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AnswerChartComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AnswerChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
