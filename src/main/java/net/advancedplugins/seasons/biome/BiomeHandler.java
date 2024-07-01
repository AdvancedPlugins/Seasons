package net.advancedplugins.seasons.biome;

import net.advancedplugins.seasons.annotation.NotCache;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.List;
import java.util.Optional;

@NotCache
public interface BiomeHandler {

  @NotNull
  @UnmodifiableView
  List<ArtificialBiome> loadedBiomes();

  default @NotNull Optional<ArtificialBiome> getBiome(@NotNull String name) {
    return loadedBiomes().stream()
        .filter(artificialBiome -> artificialBiome.name().equals(name))
        .findFirst();
  }

  @NotNull
  Optional<ArtificialBiome> getBiomeAt(@NotNull Location location);

  static @NotNull BiomeHandler instance() {
    throw new UnsupportedOperationException("Unimplemented");
  }
}