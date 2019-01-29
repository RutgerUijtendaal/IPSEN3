import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParentMeComponent } from './parent-me.component';

describe('ParentMeComponent', () => {
  let component: ParentMeComponent;
  let fixture: ComponentFixture<ParentMeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ParentMeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ParentMeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
