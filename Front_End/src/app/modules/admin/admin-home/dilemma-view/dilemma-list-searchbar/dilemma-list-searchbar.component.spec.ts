import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DilemmaListSearchbarComponent } from './dilemma-list-searchbar.component';

describe('DilemmaListSearchbarComponent', () => {
  let component: DilemmaListSearchbarComponent;
  let fixture: ComponentFixture<DilemmaListSearchbarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DilemmaListSearchbarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DilemmaListSearchbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
