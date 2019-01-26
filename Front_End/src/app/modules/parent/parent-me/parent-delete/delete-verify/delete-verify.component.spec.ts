import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteVerifyComponent } from './delete-verify.component';

describe('DeleteVerifyComponent', () => {
  let component: DeleteVerifyComponent;
  let fixture: ComponentFixture<DeleteVerifyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeleteVerifyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeleteVerifyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
