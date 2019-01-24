import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DilemmaAnswerComponent } from './dilemma-answer.component';

describe('DilemmaAnswerComponent', () => {
  let component: DilemmaAnswerComponent;
  let fixture: ComponentFixture<DilemmaAnswerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DilemmaAnswerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DilemmaAnswerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
