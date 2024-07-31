package net.advancedplugins.seasons.api;

import net.advancedplugins.seasons.api.biome.BiomeHandler;
import net.advancedplugins.seasons.api.calendar.configuration.CalendarConfigurationHandler;
import net.advancedplugins.seasons.api.temperature.TemperatureHandler;
import net.advancedplugins.seasons.api.world.WorldHandler;
import org.jetbrains.annotations.NotNull;

public final class AdvancedSeasonsAssembly {

  private AdvancedSeasonsAssembly() {}

  public static @NotNull BiomeHandler biomeHandler() {
    throw new IllegalArgumentException("Unimplemented");
  }

  public static @NotNull CalendarConfigurationHandler calendarConfigurationHandler() {
    throw new IllegalArgumentException("Unimplemented");
  }

  public static @NotNull TemperatureHandler temperatureHandler() {
    throw new IllegalArgumentException("Unimplemented");
  }

  public static @NotNull WorldHandler worldHandler() {
    throw new IllegalArgumentException("Unimplemented");
  }
}