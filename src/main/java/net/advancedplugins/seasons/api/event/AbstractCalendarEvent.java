package net.advancedplugins.seasons.api.event;

import net.advancedplugins.seasons.api.calendar.WorldCalendar;
import org.bukkit.event.world.WorldEvent;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractCalendarEvent extends WorldEvent implements CalendarEvent {

  private final @NotNull WorldCalendar calendar;

  public AbstractCalendarEvent(@NotNull WorldCalendar calendar) {
    super(calendar.world());
    this.calendar = calendar;
  }

  @Override
  public WorldCalendar calendar() {
    return calendar;
  }
}
