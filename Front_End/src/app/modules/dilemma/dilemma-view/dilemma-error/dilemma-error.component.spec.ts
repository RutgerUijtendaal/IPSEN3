import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DilemmaErrorComponent } from './dilemma-error.component';

describe('DilemmaErrorComponent', () => {
  let component: DilemmaErrorComponent;
  let fixture: ComponentFixture<DilemmaErrorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DilemmaErrorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DilemmaErrorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
