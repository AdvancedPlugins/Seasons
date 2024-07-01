package net.advancedplugins.seasons.calendar;

import net.advancedplugins.seasons.annotation.NotCache;
import net.advancedplugins.seasons.season.SeasonSnapshot;
import org.jetbrains.annotations.NotNull;

import java.time.DayOfWeek;
import java.time.Month;

@NotCache
public interface WorldCalendar {

  @NotNull YearType yearType();

  @NotNull Month currentMonth();

  @NotNull DayOfWeek currentDay();

  int elapsedDays();

  @NotNull SeasonSnapshot currentSeason();

  void setSeason(@NotNull String season);

  default int maxDays() {
    return yearType().daysPerYear();
  }
}