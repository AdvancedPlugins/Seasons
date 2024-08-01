package net.advancedplugins.seasons.api.event;

import static java.util.Objects.requireNonNull;

import net.advancedplugins.seasons.api.calendar.WorldCalendar;
import net.advancedplugins.seasons.api.season.SeasonSnapshot;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class SeasonUpdateEvent extends AbstractCalendarEvent {

  private static final HandlerList HANDLER_LIST = new HandlerList();

  private final @NotNull SeasonUpdateCause cause;
  private final @NotNull SeasonSnapshot oldState;
  private final @NotNull SeasonSnapshot newState;

  public SeasonUpdateEvent(@NotNull WorldCalendar calendar, @NotNull SeasonUpdateCause cause, @NotNull SeasonSnapshot oldState, @NotNull SeasonSnapshot newState) {
    super(calendar);
    this.oldState = requireNonNull(oldState, "oldState");
    this.newState = requireNonNull(newState, "newState");
    this.cause = requireNonNull(cause, "cause");
  }

  public static HandlerList getHandlerList() {
    return HANDLER_LIST;
  }

  public @NotNull SeasonSnapshot oldState() {
    return oldState;
  }

  public @NotNull SeasonSnapshot newState() {
    return newState;
  }

  public @NotNull SeasonUpdateCause cause() {
    return cause;
  }

  @NotNull
  @Override
  public HandlerList getHandlers() {
    return HANDLER_LIST;
  }
}