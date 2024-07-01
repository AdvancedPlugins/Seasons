package net.advancedplugins.seasons.api.temperature;

import net.advancedplugins.seasons.api.annotation.NotCache;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

/**
 * @param <T> The temperature receptor type
 */
@NotCache
public interface TemperatureReceptorHandler<T> {

  int getTemperature(@NotNull T receptor);

  int calculateTemperature(@NotNull T receptor);

  void cacheTemperature(@NotNull Collection<T> receptors, boolean cache);

  void registerModifier(@NotNull String key, @NotNull TemperatureModifier<T> modifier);

  @Nullable
  TemperatureModifier<T> removeModifier(@NotNull String key);

}