package net.advancedplugins.seasons.api.event;

public interface DateChangeEvent extends CalendarEvent {
  DateChangeCause cause();
}