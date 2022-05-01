import { TestBed } from '@angular/core/testing';

import { BookingDetailsService } from './booking-details.service';

describe('BookingDetailsService', () => {
  let service: BookingDetailsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BookingDetailsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
