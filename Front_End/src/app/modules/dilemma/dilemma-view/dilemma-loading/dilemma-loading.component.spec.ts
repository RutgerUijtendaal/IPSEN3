import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DilemmaLoadingComponent } from './dilemma-loading.component';

describe('DilemmaLoadingComponent', () => {
  let component: DilemmaLoadingComponent;
  let fixture: ComponentFixture<DilemmaLoadingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DilemmaLoadingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DilemmaLoadingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
