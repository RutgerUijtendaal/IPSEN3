import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StatisticsNavComponent } from './statistics-nav.component';

describe('StatisticsNavComponent', () => {
  let component: StatisticsNavComponent;
  let fixture: ComponentFixture<StatisticsNavComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StatisticsNavComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StatisticsNavComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
