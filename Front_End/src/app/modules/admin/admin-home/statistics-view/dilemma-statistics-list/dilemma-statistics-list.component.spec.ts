import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DilemmaListComponent } from './dilemma-statistics-list.component';

describe('DilemmaListComponent', () => {
  let component: DilemmaListComponent;
  let fixture: ComponentFixture<DilemmaListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DilemmaListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DilemmaListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
