import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CoupleListComponent } from './couple-statistics-list.component';

describe('CoupleListComponent', () => {
  let component: CoupleListComponent;
  let fixture: ComponentFixture<CoupleListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CoupleListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CoupleListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
