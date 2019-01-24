import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParentDilemmaInListComponent } from './parent-dilemma-in-list.component';

describe('ParentDilemmaInListComponent', () => {
  let component: ParentDilemmaInListComponent;
  let fixture: ComponentFixture<ParentDilemmaInListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ParentDilemmaInListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ParentDilemmaInListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
