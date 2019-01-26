import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DilemmaSuccesComponent } from './dilemma-succes.component';

describe('DilemmaSuccesComponent', () => {
  let component: DilemmaSuccesComponent;
  let fixture: ComponentFixture<DilemmaSuccesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DilemmaSuccesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DilemmaSuccesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
