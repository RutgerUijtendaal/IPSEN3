import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DilemmaDetailComponent } from './dilemma-detail.component';

describe('DilemmaDetailComponent', () => {
  let component: DilemmaDetailComponent;
  let fixture: ComponentFixture<DilemmaDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DilemmaDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DilemmaDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
