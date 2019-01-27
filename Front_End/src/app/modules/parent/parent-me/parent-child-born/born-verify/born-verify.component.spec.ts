import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BornVerifyComponent } from './born-verify.component';

describe('BornVerifyComponent', () => {
  let component: BornVerifyComponent;
  let fixture: ComponentFixture<BornVerifyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BornVerifyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BornVerifyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
