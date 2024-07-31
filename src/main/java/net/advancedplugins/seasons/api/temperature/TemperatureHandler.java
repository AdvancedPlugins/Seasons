package net.advancedplugins.seasons.api.temperature;

import net.advancedplugins.seasons.api.annotation.NotCache;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@NotCache
public interface TemperatureHandler {

  @NotNull
  TemperatureReceptorHandler<Player> players();

  @NotNull
  TemperatureReceptorHandler<Entity> entities();

  @NotNull
  TemperatureReceptorHandler<Block> blocks();

  static @NotNull TemperatureHandler instance() {
    throw new UnsupportedOperationException("Unimplemented");
  }
}