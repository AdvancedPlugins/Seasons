package net.advancedplugins.seasons.api.calendar.configuration;

import com.google.common.collect.ImmutableBiMap;
import net.advancedplugins.seasons.api.AdvancedSeasonsAssembly;
import net.advancedplugins.seasons.api.annotation.NotCache;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

@NotCache
public interface CalendarConfigurationHandler {

  static @NotNull CalendarConfigurationHandler instance() {
    return AdvancedSeasonsAssembly.calendarConfigurationHandler();
  }

  void addConfiguration(
      @NotNull String configurationName, @NotNull CalendarConfiguration calendarConfiguration)
      throws IllegalArgumentException;

  @NotNull
  Optional<CalendarConfiguration> configuration(@NotNull String name);

  @NotNull
  ImmutableBiMap<String, CalendarConfiguration> configurations();
}
