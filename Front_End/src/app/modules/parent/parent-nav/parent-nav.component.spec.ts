import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParentNavComponent } from './parent-nav.component';

describe('ParentNavComponent', () => {
  let component: ParentNavComponent;
  let fixture: ComponentFixture<ParentNavComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ParentNavComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ParentNavComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
