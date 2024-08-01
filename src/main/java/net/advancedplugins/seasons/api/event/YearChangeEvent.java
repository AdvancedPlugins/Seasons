package net.advancedplugins.seasons.api.event;

import net.advancedplugins.seasons.api.calendar.WorldCalendar;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class YearChangeEvent extends AbstractCalendarEvent implements DateChangeEvent {

  private static final HandlerList HANDLER_LIST = new HandlerList();

  private final @NotNull DateChangeCause cause;
  private final int from;
  private final int to;

  public YearChangeEvent(
      @NotNull WorldCalendar worldCalendar, @NotNull DateChangeCause cause, int from, int to) {
    super(worldCalendar);
    this.cause = cause;
    this.from = from;
    this.to = to;

    if (from < 0 || to < 0) {
      throw new IllegalArgumentException("Negative values");
    }
  }

  public static HandlerList getHandlerList() {
    return HANDLER_LIST;
  }

  @Override
  public @NotNull DateChangeCause cause() {
    return cause;
  }

  public int from() {
    return from;
  }

  public int to() {
    return to;
  }

  @Override
  public @NotNull HandlerList getHandlers() {
    return HANDLER_LIST;
  }
}
