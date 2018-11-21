import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { JoinLeagueComponent } from './join-league.component';

describe('JoinLeagueComponent', () => {
  let component: JoinLeagueComponent;
  let fixture: ComponentFixture<JoinLeagueComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ JoinLeagueComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(JoinLeagueComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
