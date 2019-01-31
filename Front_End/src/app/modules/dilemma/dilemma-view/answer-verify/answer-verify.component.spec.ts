import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AnswerVerifyComponent } from './answer-verify.component';

describe('AnswerVerifyComponent', () => {
  let component: AnswerVerifyComponent;
  let fixture: ComponentFixture<AnswerVerifyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AnswerVerifyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AnswerVerifyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
