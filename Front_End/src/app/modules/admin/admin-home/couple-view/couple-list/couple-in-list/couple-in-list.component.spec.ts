import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CoupleInListComponent } from './couple-in-list.component';

describe('CoupleInListComponent', () => {
  let component: CoupleInListComponent;
  let fixture: ComponentFixture<CoupleInListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CoupleInListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CoupleInListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
