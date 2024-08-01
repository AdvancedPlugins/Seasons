package net.advancedplugins.seasons.api.calendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import net.advancedplugins.seasons.api.calendar.configuration.CalendarConfiguration;
import net.advancedplugins.seasons.api.calendar.day.DayLengthData;
import net.advancedplugins.seasons.api.calendar.year.YearExpects;
import net.advancedplugins.seasons.api.season.SeasonSnapshot;
import org.jetbrains.annotations.NotNull;

public class CalendarSnapshot {

  private final int yearTickCount;
  private final int yearDayIndex;
  private final int year;
  private final @NotNull Month month;
  private final @NotNull DayOfWeek dayOfWeek;
  private final @NotNull SeasonSnapshot currentSeason;
  private final @NotNull CalendarConfiguration configuration;
  private final @NotNull YearExpects yearExpects;

  public CalendarSnapshot(
      int yearTickCount,
      int yearDayIndex,
      int year,
      @NotNull Month month,
      @NotNull DayOfWeek dayOfWeek,
      @NotNull SeasonSnapshot currentSeason,
      @NotNull CalendarConfiguration configuration,
      @NotNull YearExpects yearExpects) {
    this.yearTickCount = yearTickCount;
    this.yearDayIndex = yearDayIndex;
    this.year = year;
    this.month = month;
    this.dayOfWeek = dayOfWeek;
    this.currentSeason = currentSeason;
    this.configuration = configuration;
    this.yearExpects = yearExpects;
  }

  public int yearTickCount() {
    return yearTickCount;
  }

  public int yearDayIndex() {
    return yearDayIndex;
  }

  public int year() {
    return year;
  }

  public int yearDay() {
    return yearDayIndex + 1;
  }

  public @NotNull Month month() {
    return month;
  }

  public @NotNull DayOfWeek dayOfWeek() {
    return dayOfWeek;
  }

  public @NotNull LocalDate date() {
    if (configuration.type() != CalendarType.GREGORIAN)
      throw new UnsupportedOperationException("Cannot create a LocalDate with CUSTOM calendars");

    return LocalDate.ofYearDay(year, yearDay());
  }

  public @NotNull DayLengthData currentDayProportions() {
    return configuration.dayDurationBySeason().get(currentSeason.seasonType());
  }

  public @NotNull CalendarConfiguration configuration() {
    return configuration;
  }

  public @NotNull SeasonSnapshot currentSeason() {
    return currentSeason;
  }

  public @NotNull YearExpects yearExpects() {
    return yearExpects;
  }
}
