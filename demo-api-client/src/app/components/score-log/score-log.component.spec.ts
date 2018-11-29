import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ScoreLogComponent } from './score-log.component';

describe('ScoreLogComponent', () => {
  let component: ScoreLogComponent;
  let fixture: ComponentFixture<ScoreLogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ScoreLogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ScoreLogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
