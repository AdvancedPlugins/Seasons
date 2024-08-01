package net.advancedplugins.seasons.api.temperature;

import net.advancedplugins.seasons.api.AdvancedSeasonsAssembly;
import net.advancedplugins.seasons.api.annotation.NotCache;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@NotCache
public interface TemperatureHandler {

  static @NotNull TemperatureHandler instance() {
    return AdvancedSeasonsAssembly.temperatureHandler();
  }

  @NotNull
  TemperatureReceptorHandler<Player> players();

  @NotNull
  TemperatureReceptorHandler<Entity> entities();

  @NotNull
  TemperatureReceptorHandler<Block> blocks();
}
