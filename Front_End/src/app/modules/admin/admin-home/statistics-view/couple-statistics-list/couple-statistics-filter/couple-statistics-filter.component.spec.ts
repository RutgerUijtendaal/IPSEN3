import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CoupleStatisticsFilterComponent } from './couple-statistics-filter.component';

describe('CoupleStatisticsFilterComponent', () => {
  let component: CoupleStatisticsFilterComponent;
  let fixture: ComponentFixture<CoupleStatisticsFilterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CoupleStatisticsFilterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CoupleStatisticsFilterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
