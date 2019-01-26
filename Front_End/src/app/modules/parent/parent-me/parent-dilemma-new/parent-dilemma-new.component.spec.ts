import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParentDilemmaNewComponent } from './parent-dilemma-new.component';

describe('ParentDilemmaNewComponent', () => {
  let component: ParentDilemmaNewComponent;
  let fixture: ComponentFixture<ParentDilemmaNewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ParentDilemmaNewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ParentDilemmaNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
