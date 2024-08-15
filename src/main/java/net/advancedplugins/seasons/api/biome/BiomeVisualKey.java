package net.advancedplugins.seasons.api.biome;

import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public enum BiomeVisualKey {

  SKY,
  WATER,
  WATERFOG,
  GRASS,
  TREE,
  FOG;

  public @NotNull String lowercasedName() {
    return name().toLowerCase(Locale.ROOT);
  }

  public @NotNull BiomeVisualKey from(String name) {
    return valueOf(name.toLowerCase(Locale.ROOT));
  }
}