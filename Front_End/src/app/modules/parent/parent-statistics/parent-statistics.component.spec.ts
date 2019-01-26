import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParentStatisticsComponent } from './parent-statistics.component';

describe('ParentStatisticsComponent', () => {
  let component: ParentStatisticsComponent;
  let fixture: ComponentFixture<ParentStatisticsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ParentStatisticsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ParentStatisticsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
