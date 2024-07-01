package net.advancedplugins.seasons.calendar;

public enum YearType {

  ONE_DAY_BY_MONTH(12),
  GREGORIAN(365);

  private final int daysPerYear;

  YearType(int daysPerYear) {
    this.daysPerYear = daysPerYear;
  }

  public int daysPerYear() {
    return daysPerYear;
  }
}