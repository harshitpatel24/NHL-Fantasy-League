import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MemberPlayersComponent } from './member-players.component';

describe('MemberPlayersComponent', () => {
  let component: MemberPlayersComponent;
  let fixture: ComponentFixture<MemberPlayersComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MemberPlayersComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MemberPlayersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
