package net.advancedplugins.seasons.api.calendar;

import net.advancedplugins.seasons.api.annotation.NotCache;
import net.advancedplugins.seasons.api.calendar.configuration.CalendarConfiguration;
import net.advancedplugins.seasons.api.season.SeasonType;
import net.advancedplugins.seasons.api.world.WorldHandler;
import net.advancedplugins.seasons.api.world.WorldHook;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

@NotCache
public interface WorldCalendar {

  static @NotNull Optional<WorldCalendar> of(@NotNull World world) {
    return WorldHandler.instance().findCached(world).map(WorldHook::calendar);
  }

  @NotNull
  World world();

  @NotNull
  CalendarConfiguration configuration();

  @NotNull
  CalendarSnapshot calendarSnapshot();

  boolean setSeason(@NotNull SeasonType season, int transition);

  void advance();

  void setYear(int year);

  default void setYearDay(int yearDay) {
    setYearDayIndex(yearDay - 1);
  }

  void setYearDayIndex(int yearDayIndex) throws IllegalArgumentException;

  /**
   * This method skips calendar loops until reach (or exceed) the world time goal.
   *
   * @param goal The goal to reach.
   * @throws IllegalArgumentException If goal is less than 1L or major than 24000L
   */
  void tryReachWorldTime(long goal) throws IllegalArgumentException;
}
