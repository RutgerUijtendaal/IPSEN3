import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UnregisterNewComponent } from './unregister-new.component';

describe('UnregisterNewComponent', () => {
  let component: UnregisterNewComponent;
  let fixture: ComponentFixture<UnregisterNewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UnregisterNewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UnregisterNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
