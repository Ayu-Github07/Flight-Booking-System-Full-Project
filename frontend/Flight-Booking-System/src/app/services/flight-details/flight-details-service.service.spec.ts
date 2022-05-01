import { TestBed } from '@angular/core/testing';

import { FlightDetailsServiceService } from './flight-details-service.service';

describe('FlightDetailsServiceService', () => {
  let service: FlightDetailsServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FlightDetailsServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
