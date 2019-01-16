import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DilemmaInListComponent } from './dilemma-in-list.component';

describe('DilemmaInListComponent', () => {
  let component: DilemmaInListComponent;
  let fixture: ComponentFixture<DilemmaInListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DilemmaInListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DilemmaInListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
