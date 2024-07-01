package net.advancedplugins.seasons.api.temperature;

import org.jetbrains.annotations.NotNull;

/**
 * Calculates additional temperature in celsius;
 *
 * @param <T> The temperature receptor type.
 */
public interface TemperatureModifier<T> {

  int calculateAdditionalTemperature(@NotNull T receptor, int actual);

}