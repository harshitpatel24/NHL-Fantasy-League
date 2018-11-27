import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewLeagueComponent } from './view-league.component';

describe('ViewLeagueComponent', () => {
  let component: ViewLeagueComponent;
  let fixture: ComponentFixture<ViewLeagueComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewLeagueComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewLeagueComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
