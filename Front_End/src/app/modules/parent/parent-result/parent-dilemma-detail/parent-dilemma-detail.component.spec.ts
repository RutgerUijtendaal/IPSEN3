import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParentDilemmaDetailComponent } from './parent-dilemma-detail.component';

describe('ParentDilemmaDetailComponent', () => {
  let component: ParentDilemmaDetailComponent;
  let fixture: ComponentFixture<ParentDilemmaDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ParentDilemmaDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ParentDilemmaDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
