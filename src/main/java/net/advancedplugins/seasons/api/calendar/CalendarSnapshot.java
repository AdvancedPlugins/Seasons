package net.advancedplugins.seasons.api.calendar;

import net.advancedplugins.seasons.api.calendar.configuration.CalendarConfiguration;
import net.advancedplugins.seasons.api.calendar.day.DayLengthData;
import net.advancedplugins.seasons.api.calendar.year.YearExpects;
import net.advancedplugins.seasons.api.season.SeasonSnapshot;
import org.jetbrains.annotations.NotNull;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

public record CalendarSnapshot(
    int yearTickCount,
    int yearDayIndex,
    int year,
    @NotNull Month month,
    @NotNull DayOfWeek dayOfWeek,
    @NotNull SeasonSnapshot currentSeason,
    @NotNull CalendarConfiguration configuration,
    @NotNull YearExpects yearExpects
) {

  public int yearDay() {
    return yearDayIndex + 1;
  }

  public @NotNull LocalDate date() {
    if (configuration.type() != CalendarType.GREGORIAN)
      throw new UnsupportedOperationException("Cannot create a LocalDate with CUSTOM calendars");

    return LocalDate.ofYearDay(year, yearDay());
  }

  public @NotNull DayLengthData currentDayProportions() {
    return configuration.dayDurationBySeason().get(currentSeason.seasonType());
  }
}