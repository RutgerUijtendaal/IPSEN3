import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminInListComponent } from './admin-in-list.component';

describe('AdminInListComponent', () => {
  let component: AdminInListComponent;
  let fixture: ComponentFixture<AdminInListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminInListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminInListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
