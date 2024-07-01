package net.advancedplugins.seasons.calendar;

import net.advancedplugins.seasons.annotation.NotCache;
import net.advancedplugins.seasons.season.SeasonType;
import org.jetbrains.annotations.NotNull;

@NotCache
public interface WorldCalendar {

  @NotNull
  CalendarConfiguration configuration();

  @NotNull
  CalendarSnapshot calendarSnapshot();

  void setSeason(@NotNull SeasonType season);

}