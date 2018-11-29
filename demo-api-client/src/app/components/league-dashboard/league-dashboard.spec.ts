import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LeagueDashboardComponent } from './league-dashboard.component';

describe('LeagueDashboardComponent', () => {
  let component: LeagueDashboardComponent;
  let fixture: ComponentFixture<LeagueDashboardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LeagueDashboardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LeagueDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
