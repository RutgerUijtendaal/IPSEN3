import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminListSearchbarComponent } from './admin-list-searchbar.component';

describe('AdminListSearchbarComponent', () => {
  let component: AdminListSearchbarComponent;
  let fixture: ComponentFixture<AdminListSearchbarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminListSearchbarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminListSearchbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
