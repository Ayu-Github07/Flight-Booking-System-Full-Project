import { TestBed } from '@angular/core/testing';

import { HasRoleGuardGuard } from './has-role-guard.guard';

describe('HasRoleGuardGuard', () => {
  let guard: HasRoleGuardGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(HasRoleGuardGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
