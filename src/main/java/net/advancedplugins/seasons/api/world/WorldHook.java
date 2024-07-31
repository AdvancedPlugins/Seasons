package net.advancedplugins.seasons.api.world;

import net.advancedplugins.seasons.api.calendar.WorldCalendar;
import net.advancedplugins.seasons.api.calendar.configuration.CalendarConfiguration;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

public interface WorldHook {

  @NotNull World world();

  @NotNull WorldCalendar calendar();

  boolean isPersistent();

  void setPersistent(boolean persistent);

  void setCalendarConfiguration(@NotNull CalendarConfiguration calendarConfiguration);

  boolean isStillLoaded();

}