import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CoupleListSearchbarComponent } from './couple-list-searchbar.component';

describe('CoupleListSearchbarComponent', () => {
  let component: CoupleListSearchbarComponent;
  let fixture: ComponentFixture<CoupleListSearchbarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CoupleListSearchbarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CoupleListSearchbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
