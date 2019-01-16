import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DilemmaViewComponent } from './dilemma-view.component';

describe('DilemmaViewComponent', () => {
  let component: DilemmaViewComponent;
  let fixture: ComponentFixture<DilemmaViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DilemmaViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DilemmaViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
