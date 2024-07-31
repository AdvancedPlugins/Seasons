package net.advancedplugins.seasons.api.calendar;

import com.google.common.collect.ImmutableBiMap;
import net.advancedplugins.seasons.api.AdvancedSeasonsAssembly;
import net.advancedplugins.seasons.api.annotation.NotCache;
import net.advancedplugins.seasons.api.calendar.configuration.CalendarConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

@NotCache
public interface CalendarConfigurationHandler {

  void addConfiguration(@NotNull String configurationName, @NotNull CalendarConfiguration calendarConfiguration)
    throws IllegalArgumentException;

  @NotNull
  Optional<CalendarConfiguration> configuration(@NotNull String name);

  @NotNull
  ImmutableBiMap<String, CalendarConfiguration> configurations();

  static @NotNull CalendarConfigurationHandler instance() {
    return AdvancedSeasonsAssembly.calendarConfigurationHandler();
  }
}