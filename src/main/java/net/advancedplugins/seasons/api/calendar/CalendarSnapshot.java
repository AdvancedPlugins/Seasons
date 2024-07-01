package net.advancedplugins.seasons.api.calendar;

import net.advancedplugins.seasons.api.season.SeasonType;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.Month;

public record CalendarSnapshot(
    int currentYearTicks,
    int year,
    int daysPerYear,
    Month currentMonth,
    @NotNull SeasonType currentSeason,
    @NotNull CalendarConfiguration configuration
) {

  public int elapsedDays() {
    int ticksPerDay = configuration.dayDurationInTicks();

    return currentYearTicks / ticksPerDay;
  }

  public int currentDayNumber() {
    int ticksPerDay = configuration.dayDurationInTicks();

    return (int) Math.ceil((double) currentYearTicks / ticksPerDay);
  }

  public @NotNull LocalDate date() {
    return LocalDate.ofYearDay(year, currentDayNumber());
  }
}