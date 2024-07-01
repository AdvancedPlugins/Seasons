package net.advancedplugins.seasons.api.calendar;

import net.advancedplugins.seasons.api.annotation.NotCache;
import net.advancedplugins.seasons.api.season.SeasonType;
import org.jetbrains.annotations.NotNull;

@NotCache
public interface WorldCalendar {

  @NotNull
  CalendarConfiguration configuration();

  @NotNull
  CalendarSnapshot calendarSnapshot();

  void setSeason(@NotNull SeasonType season);

}