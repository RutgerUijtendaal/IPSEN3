import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParentDilemmaListComponent } from './parent-dilemma-list.component';

describe('ParentDilemmaListComponent', () => {
  let component: ParentDilemmaListComponent;
  let fixture: ComponentFixture<ParentDilemmaListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ParentDilemmaListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ParentDilemmaListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
