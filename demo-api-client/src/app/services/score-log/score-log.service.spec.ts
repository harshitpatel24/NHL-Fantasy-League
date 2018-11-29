import { TestBed } from '@angular/core/testing';

import { ScoreLogService } from './score-log.service';

describe('ScoreLogService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ScoreLogService = TestBed.get(ScoreLogService);
    expect(service).toBeTruthy();
  });
});
