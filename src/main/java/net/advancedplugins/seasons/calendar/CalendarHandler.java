package net.advancedplugins.seasons.calendar;

import net.advancedplugins.seasons.annotation.NotCache;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

@NotCache
public interface CalendarHandler {

  @NotNull Optional<WorldCalendar> calendarOf(@NotNull World world);

  @NotNull Optional<WorldCalendar> computeCalendar(@NotNull World world);

  boolean deleteCalendar(@NotNull World world);

  static @NotNull CalendarHandler instance() {
    throw new UnsupportedOperationException("Unimplemented");
  }
}