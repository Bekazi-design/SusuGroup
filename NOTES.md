# Design Notes

Fixed bugs:
1. closeWeek was not clearing weeklyContributions correctly, left debt.
2. recordContribution allowed overpay > weeklyAmount.

Used BigDecimal for money to avoid floating errors.

Tests: 7 tests covering partial, cannot close, payout order, duplicate, overpay, unknown.

Time: 3 hours.