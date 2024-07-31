package net.advancedplugins.seasons.api.world;

import com.google.common.collect.ImmutableMap;
import net.advancedplugins.seasons.api.AdvancedSeasonsAssembly;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface WorldHandler {

  @NotNull WorldHook hookInto(@NotNull World world, boolean skipLoad);

  @NotNull Optional<WorldHook> loadFromWorld(@NotNull World world);

  @NotNull Optional<WorldHook> findCached(@NotNull World world);

  @NotNull
  ImmutableMap<World, WorldHook> allCached();

  boolean removeHook(@NotNull World world);

  boolean hasHook(@NotNull World world);

  static @NotNull WorldHandler instance() {
    return AdvancedSeasonsAssembly.worldHandler();
  }
}