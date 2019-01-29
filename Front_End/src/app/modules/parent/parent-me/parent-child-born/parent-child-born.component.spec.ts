import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParentChildBornComponent } from './parent-child-born.component';

describe('ParentChildBornComponent', () => {
  let component: ParentChildBornComponent;
  let fixture: ComponentFixture<ParentChildBornComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ParentChildBornComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ParentChildBornComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
