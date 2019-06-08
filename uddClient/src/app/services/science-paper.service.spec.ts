import { TestBed } from '@angular/core/testing';

import { SciencePaperService } from './science-paper.service';

describe('SciencePaperService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SciencePaperService = TestBed.get(SciencePaperService);
    expect(service).toBeTruthy();
  });
});
